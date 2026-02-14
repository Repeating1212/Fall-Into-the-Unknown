package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.RatConfig;
import Level.BaseLevel.Manager.Observer;

import java.util.ArrayList;

public class Rat extends ImageObject {

    private final Behaviour tackle;

    public Rat(ArrayList<Property> properties) {
        super(RatConfig.getProperty(), ImageLoader.L01_RAT, RatConfig.DEAD_DURATION);
        this.tackle = RatConfig.getTackle(this.property);
        property.spawnNearBoundary(properties);
    }

    // Override Method

    @Override
    public void updateAlive(double deltaTime, Observer observer) {
        property.pointTo(observer.getPlayerPosition());
        handleAttack(deltaTime, observer);
        super.updateAlive(deltaTime, observer);
    }

    // Private Method

    private void handleAttack(double deltaTime, Observer observer){
        tackle.update(deltaTime, observer);
    }
}
