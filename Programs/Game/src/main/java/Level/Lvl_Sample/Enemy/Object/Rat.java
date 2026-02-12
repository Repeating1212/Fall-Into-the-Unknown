package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.RatConfig;
import Level.BaseLevel.Manager.Observer;

import java.util.ArrayList;

public class Rat extends ImageObject {

    private final Tackle tackle;

    public Rat(ArrayList<Property> properties) {
        super(RatConfig.getProperty(), ImageLoader.L01_RAT);
        this.tackle = RatConfig.getTackle();
        do {
            property.spawnNearBoundary();
        } while (property.isCollide(properties));

    }

    // Override Method

    @Override
    public void update(double deltaTime, Observer observer) {
        super.update(deltaTime, observer);
        property.pointTo(observer.getPlayerPosition());
        updateSpritePosition();
        handleAttack(deltaTime, observer);
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);
        super.updateSpritePosition();
    }

    // Private Method

    private void handleAttack(double deltaTime, Observer observer){
        tackle.update(property, observer.getHealthObj(), deltaTime);
    }
}
