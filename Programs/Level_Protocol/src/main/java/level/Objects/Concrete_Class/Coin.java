package level.Objects.Concrete_Class;

import level.Data.Config.CoinConfig;
import level.Data.Properties.HitBox;
import level.Data.Properties.Property;
import level.Data.Suppliers.CoinSupplier;
import level.Data.Properties.Position;
import level.Managers.Observer;
import level.Objects.Base_Class.ImageObject;

import java.util.ArrayList;
import java.util.random.RandomGenerator;

public class Coin extends ImageObject {
    private boolean collected = false;

    public Coin(Property property, Observer observer) {
        super( CoinSupplier.getProperty(property),
                CoinConfig.loadImage(), observer
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
