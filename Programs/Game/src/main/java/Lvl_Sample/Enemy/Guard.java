package Lvl_Sample.Enemy;

import Data.Loader.ImageLoader;
import Lvl_Sample.Behaviour.GroundSlap;
import Lvl_Sample.Managers.Observer;
import BaseLevel.Objects.Class_Base.Boss;
import Lvl_Sample.Data.Config.GuardConfig;
import Lvl_Sample.Data.Suppliers.GuardSupplier;
import BaseLevel.View.AttackVisualize.AttackVisual;

public class Guard extends Boss {

//    private final AttackSkill attackSkill;
    private final GroundSlap groundSlap;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard(Observer observer) {
        super(GuardSupplier.getProperty(), ImageLoader.STAKE, observer);
        this.groundSlap = GuardSupplier.getGroundSlap();
        groundSlap.initializeData(property);
        observer.addDisplayableObject(this);
        observer.addDisplayableObject(groundSlap.getAttackVisual());
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        property.pointTo(observer.getEnemy().getProperty());
        super.update(deltaTime);
        updateSpritePosition();
        handleAttack(deltaTime);
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);
        super.updateSpritePosition();
    }

    @Override
    public void removeDisplay(){
        observer.removeDisplayableObject(groundSlap.getAttackVisual());
    }


    // Private Method

    private void handleAttack(double deltaTime){
        if (isNearEnemy()) groundSlap.activate(observer.getEnemy().getCenterPos());
        groundSlap.handleRigid();
        groundSlap.update(deltaTime, observer);
    }

    private boolean isNearEnemy(){
        double horizontalDistance = Math.abs(property.getX() - observer.getEnemy().getX());
        double verticalDistance = Math.abs(property.getY() - observer.getEnemy().getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
