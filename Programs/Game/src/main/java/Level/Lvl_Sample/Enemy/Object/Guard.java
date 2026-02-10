package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Level.Lvl_Sample.Behaviour.GroundSlap;
import Level.Lvl_Sample.Managers.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;

import java.util.ArrayList;

public class Guard extends Boss {

    private final GroundSlap groundSlap;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard(Observer observer) {
        super(GuardConfig.getProperty(), ImageLoader.STAKE, observer);
        this.groundSlap = GuardConfig.getGroundSlap();
        groundSlap.initializeData(property);
        displays.add(groundSlap.getAttackVisual());
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
