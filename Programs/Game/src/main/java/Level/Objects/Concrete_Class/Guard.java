package Level.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Level.Managers.Observer;
import Level.Objects.Base_Class.Boss;
import Level.Data.Config.GuardConfig;
import Level.Data.Suppliers.GuardSupplier;
import Level.View.AttackVisualize.AttackVisual;

public class Guard extends Boss {

//    private final AttackSkill attackSkill;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard(Observer observer) {
        super(GuardSupplier.getProperty(), ImageLoader.STAKE, observer);
//        this.attackSkill = GuardSupplier.getAttackBehaviour();
//        this.attackSkill.initializeData(property, null);
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        property.pointTo(observer.getEnemy().getProperty());
        super.update(deltaTime);
        updateSpritePosition();
//        handleAttack(deltaTime);
    }

    @Override
    public AttackVisual getAttackVisual(){
        return  null;
//        return attackSkill.getAttackVisual();
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);
        super.updateSpritePosition();
    }


    // Private Method

//    private void handleAttack(double deltaTime){
//        if (isNearEnemy()) attackSkill.activate(observer.getEnemy().getCenterPos());
//        attackSkill.handleRigid();
//        attackSkill.update(deltaTime, observer);
//    }

    private boolean isNearEnemy(){
        double horizontalDistance = Math.abs(property.getX() - observer.getEnemy().getX());
        double verticalDistance = Math.abs(property.getY() - observer.getEnemy().getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
