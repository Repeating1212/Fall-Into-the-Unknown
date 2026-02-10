package Lvl_Sample.Behaviour;

import BaseLevel.Properties.Property;
import BaseLevel.Objects.Class_Base.GameObject;
import Data.DataClass.Timer;

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
            boolean damaged = enemy.takeDamage(Damage);
            timer.setPending();

            if (damaged) handleRigid(property);
        }
        timer.update(deltaTime);
    }

    private void handleRigid(Property property){
        if (timer.isPending()){
            property.pauseMovement(AttackRigid);
            timer.start();
        }
    }
}
