package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.DeadAnimation;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;

import java.util.ArrayList;

public class SlimeGrey extends ImageObject {

    private final int size;

    private DeadAnimation deadAnimation = new DeadAnimation(sprite, SlimeGreyConfig.DEAD_DURATION);
    private Tackle tackle;
    private boolean isSpawned = false;

    public SlimeGrey(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeGrey(ArrayList<Property> properties, int size){
        super(SlimeGreyConfig.getProperty(size), ImageLoader.L01_SLIME_GREY);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size);
        property.spawnNearBoundary(properties);
    }

    // Spawned slime
    public SlimeGrey (ArrayList<Property> properties, int size, Position destination){
        super(SlimeGreyConfig.getProperty(size), ImageLoader.L01_SLIME_GREY);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size);
        property.spawnNearBy(properties , destination);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        handleDeadAnimation();
        super.update(deltaTime, observer);

        if (isAlive()){
            property.pointTo(observer.getPlayerPosition());
            tackle.update(this.property, observer.getHealthObj(), deltaTime);
        }
    }

    public int getSize(){
        return size;
    }

    public boolean spawnable(){
        return (size > 1 &&
                ! isSpawned &&
                deadAnimation.isEnd());
    }

    public void setSpawned(){
        isSpawned = true;
    }

    @Override
    public boolean removeCondition(){
        return deadAnimation.isEnd();
    }

    private void handleDeadAnimation(){
        if (property.isDead()){
            deadAnimation.play();
        }
    }
}
