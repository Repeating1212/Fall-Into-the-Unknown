package Level.Lvl_2.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.Enemy;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Enemy.Config.RatConfig;
import Level.Lvl_2.Enemy.Config.SkeletonConfig;

import java.util.ArrayList;

public class Skeleton extends Enemy {


    public Skeleton(ArrayList<Property> properties) {
        super(SkeletonConfig.getProperty(), ImageLoader.L01_RAT, RatConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                SkeletonConfig.getTackle(this.property)
        };
    }
}
