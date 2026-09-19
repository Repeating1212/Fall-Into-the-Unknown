package Level.Lvl_2.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.Enemy;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Behaviour.PointToward;
import Level.Lvl_1.Enemy.Config.RatConfig;
import Level.Lvl_2.Enemy.Behaviour.SpeedUp;
import Level.Lvl_2.Enemy.Config.SkeletonConfig;

import java.util.ArrayList;

public class Skeleton extends Enemy {

    private SpeedUp speedUp;
    private boolean activeSpeedUp = false; // Speed Up when Recover From

    public Skeleton(ArrayList<Property> properties) {
        super(SkeletonConfig.getProperty(), ImageLoader.L02_Skeleton, RatConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
        speedUp = SkeletonConfig.getSpeedUp(this.property);
        speedUp.setAutoActivate(false);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                new PointToward(property),
                SkeletonConfig.getTackle(this.property),
        };
    }

    @Override
    public void updateAlive(double deltaTime, Observer observer) {
        handleInvincibilityAnimation();
        super.updateAlive(deltaTime, observer);
        if(activeSpeedUp && !property.status.isPauseMovement()){
            speedUp.activate(observer);
            activeSpeedUp = false;
        }
    }

    @Override
    public boolean takeDamage(double damage) {

        boolean isDamaged = super.takeDamage(damage);
        if (!property.status.isInvincible()){
            property.setInvincible(SkeletonConfig.DAMAGE_RIGID_DURATION);
            property.pauseMovement(SkeletonConfig.DAMAGE_RIGID_DURATION);
            property.pauseDirect(SkeletonConfig.DAMAGE_RIGID_DURATION);
            property.status.pauseAttack(SkeletonConfig.DAMAGE_RIGID_DURATION);
            handleHeightChanges();
            activeSpeedUp = true;
        }
        return isDamaged;
    }

    private void handleInvincibilityAnimation(){
        if(property.isInvincible()) {
            sprite.setImage(ImageLoader.L02_Skeleton_Invincible);
        } else{
            sprite.setImage(ImageLoader.L02_Skeleton);
        }
    }

    private void handleHeightChanges(){
        SkeletonConfig.SkeletonState skeletonState = SkeletonConfig.SkeletonState.checkState(property.getCurrentHealth());
        int height = skeletonState.getHeight();
        int speed  = skeletonState.getSpeed();
        property.setHeight(height);
        property.setBaseSpeed(speed);
        super.resetSprite();
    }
}
