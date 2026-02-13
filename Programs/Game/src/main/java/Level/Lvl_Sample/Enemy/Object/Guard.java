package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.Lvl_Sample.Behaviour.GroundSlap;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;

public class Guard extends Boss {

    private final GroundSlap groundSlap;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard() {
        super(GuardConfig.getProperty(), ImageLoader.STAKE);
        this.groundSlap = GuardConfig.getGroundSlap();
        groundSlap.initializeData(property);
    }

    // Override Method

    @Override
    public void update(double deltaTime, Observer observer) {
        property.pointTo(observer.getPlayerPosition());
        super.update(deltaTime, observer);
        handleAttack(deltaTime, observer);

        if(groundSlap.getAttackVisual().isActive()){
            relatedDisplay.add(groundSlap.getAttackVisual());
        } else {
            relatedDisplay.remove(groundSlap.getAttackVisual());
        }
    }

    // Private Method

    private void handleAttack(double deltaTime, Observer observer){
        if (isNearEnemy(observer.getPlayer())) groundSlap.activate(observer.getPlayer().getCenterPos());
        groundSlap.handleRigid();
        groundSlap.update(deltaTime, observer);
    }

    private boolean isNearEnemy(GameObject enemy){
        double horizontalDistance = Math.abs(property.getX() - enemy.getX());
        double verticalDistance = Math.abs(property.getY() - enemy.getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
