package Level.Lvl_Sample.Enemy.Object;

import Data.DataClass.ArrayData;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.Lvl_Sample.Behaviour.GroundSlap;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;

public class Guard extends Boss {

    private final GroundSlap groundSlap;

    public Guard() {
        super(GuardConfig.getProperty(), ImageLoader.STAKE, GuardConfig.DEAD_DURATION);
        this.groundSlap = GuardConfig.getGroundSlap(this.property);
    }

    // Override Method

    @Override
    public void update(double deltaTime, Observer observer) {
        property.pointTo(observer.getPlayerPosition());
        groundSlap.update(deltaTime, observer);
        super.update(deltaTime, observer);
    }

    @Override
    public ArrayData<DisplayableObject> reloadDisplay(){
        ArrayData<DisplayableObject> display = super.reloadDisplay();
        if(groundSlap.isRunning()) display.add(groundSlap.getVisual());

        return display;
    }
}
