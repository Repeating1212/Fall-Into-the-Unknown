package Level.Lvl_1.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Enemy.Config.RatConfig;
import Level.BaseLevel.Objects.Class_Base.Enemy;

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
