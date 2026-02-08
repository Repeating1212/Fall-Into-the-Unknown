package Level.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Level.Skills.Attacks.AttackSkill;
import Level.Managers.Observer;
import Level.Objects.Base_Class.Boss;
import Level.Data.Config.GuardConfig;
import Level.Data.Suppliers.GuardSupplier;
import Level.Skills.Boss.GuardMove;
import Level.Objects.Base_Class.GameObject;
import Level.View.AttackVisualize.AttackVisual;

public class Guard extends Boss {

    private final AttackSkill attackSkill;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard(GameObject enemy, Observer observer) {
        super(GuardSupplier.getProperty(), ImageLoader.STAKE,
                enemy, observer);
        this.attackSkill = GuardSupplier.getAttackBehaviour();
        this.attackSkill.initializeData(property, null);
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        GuardMove.directTowardPlayer(enemy.getProperty(), this.property);
        super.update(deltaTime);
        updateSpritePosition();
        handleAttack(deltaTime);
    }

    @Override
    public AttackVisual getAttackVisual(){
        return attackSkill.getAttackVisual();
    }

    // Private Method

    private void handleAttack(double deltaTime){
        if (isNearEnemy()) attackSkill.activate(enemy.getCenterPos());
        attackSkill.handleRigid();
        attackSkill.update(deltaTime, observer);
    }

    private boolean isNearEnemy(){
        double horizontalDistance = Math.abs(property.getX() - enemy.getX());
        double verticalDistance = Math.abs(property.getY() - enemy.getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
