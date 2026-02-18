package level.Objects.Concrete_Class;

import level.Skills.Attacks.AttackSkill;
import level.Managers.Observer;
import level.View.AttackVisualize.AttackVisual;
import level.Objects.Base_Class.Boss;
import level.Data.Config.GuardConfig;
import level.Data.Suppliers.GuardSupplier;
import level.Skills.Boss.GuardMove;
import level.Objects.Base_Class.GameObject;


import java.util.ArrayList;

public class Guard extends Boss {

    private final AttackSkill attackSkill;
    private final double ATTACK_OFFSET = GuardConfig.ATTACK_DISTANCE_OFFSET;

    public Guard(GameObject enemy, Observer observer) {
        super(GuardSupplier.getProperty(), GuardConfig.loadImage(),
                enemy, observer);
        this.attackSkill = GuardSupplier.getAttackBehaviour(property);
    }

    // Override Method

    @Override
    public void update(double deltaTime) {
        GuardMove.directTowardPlayer(enemy.getProperty(), this.property);
        super.update(deltaTime);
        updateSpritePosition();
        handleAttack(observer.getLivingEntities(), deltaTime);
    }

    @Override
    public AttackVisual getAttackVisual(){
        return attackSkill.getAttackVisual();
    }

    // Private Method

    private void handleAttack(ArrayList<GameObject> healthObj, double deltaTime){
        if (isNearEnemy()) attackSkill.setAttack(enemy.getCenterPos(), healthObj);
        attackSkill.handleAttackRigid(property);
        attackSkill.update(deltaTime);
    }

    private boolean isNearEnemy(){
        double horizontalDistance = Math.abs(property.getX() - enemy.getX());
        double verticalDistance = Math.abs(property.getY() - enemy.getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
