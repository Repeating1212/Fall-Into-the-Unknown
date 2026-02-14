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
        super(GuardConfig.getProperty(), ImageLoader.STAKE, GuardConfig.DEAD_DURATION);
        this.groundSlap = GuardConfig.getGroundSlap(this.property);
    }

    // Override Method

    @Override
    public void update(double deltaTime, Observer observer) {
        property.pointTo(observer.getPlayerPosition());
        super.update(deltaTime, observer);
        groundSlap.update(deltaTime, observer);

        if(groundSlap.isRunning()){
            relatedDisplay.add(groundSlap.getVisual());
        } else {
            relatedDisplay.remove(groundSlap.getVisual());
        }
    }
}
