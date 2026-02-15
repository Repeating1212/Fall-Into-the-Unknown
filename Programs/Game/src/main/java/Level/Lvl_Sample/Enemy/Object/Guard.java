package Level.Lvl_Sample.Enemy.Object;

import Data.DataClass.ArrayData;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;
import Level.Lvl_Sample.Enemy.Interface.Enemy;

public class Guard extends Enemy {


    public Guard() {
        super(GuardConfig.getProperty(), ImageLoader.STAKE, GuardConfig.DEAD_DURATION);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                GuardConfig.getGroundSlap(this.property)
        };
    }
}
