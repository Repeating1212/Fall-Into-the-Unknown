package BaseLevel.Objects.Class_Concrete;

import BaseLevel.Objects.Class_Base.ImageObject;
import Data.Loader.ImageLoader;
import BaseLevel.Objects.Config.CoinConfig;
import BaseLevel.Properties.Property;
import BaseLevel.Objects.Supplier.CoinSupplier;
import Lvl_Sample.Managers.Observer;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Coin extends ImageObject {
    private boolean collected = false;

    public Coin(Property property, Observer observer) {
        super( CoinSupplier.getProperty(property),
                ImageLoader.COIN_ANIMATION, observer
        );
        randomizePosition();
    }

    @Override
    public void update(double deltaTime){
        if (collected) return;
        collected = property.isCollide(observer.getPlayerProperty());
    }

    public boolean isCollected(){
        return collected;
    }

    @Override
    public double getCenterX() {
        return property.getX();
    }

    @Override
    public double getCenterY() {
        return property.getY();
    }

    // Private Method

    private void randomizePosition(){
        RandomGenerator random = RandomGenerator.getDefault();
        double randomX = random.nextInt(CoinConfig.RANDOMIZE_SPAWN_MIN, CoinConfig.RANDOMIZE_SPAWN_MAX);
        double randomY = random.nextInt(CoinConfig.RANDOMIZE_SPAWN_MIN, CoinConfig.RANDOMIZE_SPAWN_MAX);
        property.move(randomX, randomY, new ArrayList<>());
        updateSpritePosition();
    }
}
