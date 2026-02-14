package Level.Lvl_Sample.Enemy.Object;

import Data.DataClass.ArrayData;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;

public class Guard extends Boss {

    private final Behaviour groundSlap;

    public Guard() {
        super(GuardConfig.getProperty(), ImageLoader.STAKE, GuardConfig.DEAD_DURATION);
        this.groundSlap = GuardConfig.getGroundSlap(this.property);
    }

    // Override Method

    @Override
    public void updateAlive(double deltaTime, Observer observer) {
        property.pointTo(observer.getPlayerPosition());
        groundSlap.update(deltaTime, observer);
        super.updateAlive(deltaTime, observer);
    }

    @Override
    public ArrayData<DisplayableObject> reloadDisplay(){
        ArrayData<DisplayableObject> display = super.reloadDisplay();
        if(groundSlap.isRunning() && deadAnimation.isDeactive()) {
            display.add(groundSlap.getVisual());
        }
        return display;
    }
}
