package Level.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Level.Behaviour.MeleeAttack;
import Level.Data.Config.GuardConfig;
import Level.Data.Suppliers.GuardSupplier;
import Level.Data.Suppliers.RatSupplier;
import Level.Managers.Observer;
import Level.Objects.Base_Class.Boss;
import Level.Objects.Base_Class.GameObject;
import Level.View.AttackVisualize.AttackVisual;

public class Rat extends Boss {

    private final MeleeAttack meleeAttack;

    public Rat(GameObject enemy, Observer observer) {
        super(RatSupplier.getProperty(), ImageLoader.L01_RAT,
                enemy, observer);
        this.meleeAttack = RatSupplier.getMeleeAttack();
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        property.pointTo(enemy.getProperty());
        super.update(deltaTime);
        updateSpritePosition();
        handleAttack(deltaTime);
    }

    @Override
    public AttackVisual getAttackVisual(){
        return null;
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);
        super.updateSpritePosition();
    }


    // Private Method

    private void handleAttack(double deltaTime){
        meleeAttack.update(property, enemy, deltaTime);
        meleeAttack.handleRigid(property);
    }
}
