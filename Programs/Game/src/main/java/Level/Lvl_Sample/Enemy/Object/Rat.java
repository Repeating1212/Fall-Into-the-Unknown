package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.GuardConfig;
import Level.Lvl_Sample.Enemy.Config.RatConfig;
import Level.BaseLevel.Manager.Observer;
import Level.Lvl_Sample.Enemy.Interface.Enemy;

import java.util.ArrayList;

public class Rat extends Enemy {


    public Rat(ArrayList<Property> properties) {
        super(RatConfig.getProperty(), ImageLoader.L01_RAT, RatConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                RatConfig.getTackle(this.property)
        };
    }
}
