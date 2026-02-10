package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.Lvl_Sample.Behaviour.MeleeAttack;
import Level.Lvl_Sample.Enemy.Config.RatConfig;
import Level.Lvl_Sample.Managers.Observer;

public class Rat extends ImageObject {

    private final MeleeAttack meleeAttack;

    public Rat(Observer observer) {
        super(RatConfig.getProperty(), ImageLoader.L01_RAT, observer);
        this.meleeAttack = RatConfig.getMeleeAttack();
        property.spawnNearBoundary();
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        property.pointTo(observer.getEnemy().getProperty());
        updateSpritePosition();
        handleAttack(deltaTime);
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);
        super.updateSpritePosition();
    }

    // Private Method

    private void handleAttack(double deltaTime){
        meleeAttack.update(property, observer.getEnemy(), deltaTime);
    }
}
