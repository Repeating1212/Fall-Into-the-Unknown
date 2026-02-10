package Lvl_Sample.Enemy;

import Data.Loader.ImageLoader;
import Lvl_Sample.Behaviour.MeleeAttack;
import Lvl_Sample.Data.Suppliers.RatSupplier;
import Lvl_Sample.Managers.Observer;
import BaseLevel.Objects.Class_Base.Boss;
import BaseLevel.View.AttackVisualize.AttackVisual;

public class Rat extends Boss {

    private final MeleeAttack meleeAttack;

    public Rat(Observer observer) {
        super(RatSupplier.getProperty(), ImageLoader.L01_RAT, observer);
        this.meleeAttack = RatSupplier.getMeleeAttack();
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        property.pointTo(observer.getEnemy().getProperty());
        super.update(deltaTime);
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
