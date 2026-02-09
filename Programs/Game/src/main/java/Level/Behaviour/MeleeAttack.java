package Level.Behaviour;

import Level.Data.Properties.Property;
import Level.Managers.Observer;
import Level.Objects.Base_Class.GameObject;
import Level.Skills.Timer;

public class MeleeAttack {

    private final double Damage;
    private final double AttackRigid;
    private final Timer timer;

    public MeleeAttack(double damage, double attackRigid){
        this.Damage = damage;
        this.AttackRigid = attackRigid;
        this.timer = new Timer(attackRigid);
    }

    public void update(Property property, GameObject enemy, double deltaTime){
        if (property.isTouch(enemy.getProperty()) && timer.isEnd()){
            enemy.takeDamage(Damage);
            timer.setPending();
        }
        timer.update(deltaTime);
    }

    public void handleRigid(Property property){
        if (timer.isPending()){
            property.pauseMovement(AttackRigid);
            timer.start();
        }
    }
}
