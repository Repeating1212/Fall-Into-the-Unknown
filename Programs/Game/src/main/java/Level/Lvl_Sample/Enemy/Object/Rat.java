package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.Lvl_Sample.Behaviour.MeleeAttack;
import Level.Lvl_Sample.Enemy.Config.RatConfig;
import Level.BaseLevel.Manager.Observer;

public class Rat extends ImageObject {

    private final MeleeAttack meleeAttack;

    public Rat() {
        super(RatConfig.getProperty(), ImageLoader.L01_RAT);
        this.meleeAttack = RatConfig.getMeleeAttack();
        property.spawnNearBoundary();
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
        meleeAttack.update(property, observer.getHealthObj(), deltaTime);
    }
}
