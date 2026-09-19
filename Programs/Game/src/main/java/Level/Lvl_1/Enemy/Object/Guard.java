package Level.Lvl_1.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Behaviour.PointToward;
import Level.Lvl_1.Enemy.Config.GuardConfig;
import Level.BaseLevel.Objects.Class_Base.Enemy;

public class Guard extends Enemy {


    public Guard() {
        super(GuardConfig.getProperty(), ImageLoader.STAKE, GuardConfig.DEAD_DURATION);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                GuardConfig.getGroundSlap(this.property),
                new PointToward(this.property)
        };
    }
}
