package Level.Lvl_Sample.Behaviour;

import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Data.DataClass.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeleeAttack {

    private final double Damage;
    private final double AttackRigid;
    private final Timer timer;

    private final List<Class<? extends GameObject>> TARGET = Arrays.asList(
            Player.class
    );

    public MeleeAttack(double damage, double attackRigid){
        this.Damage = damage;
        this.AttackRigid = attackRigid;
        this.timer = new Timer(attackRigid);
    }

    public void update(Property property, ArrayList<GameObject> HealthObj, double deltaTime){

        timer.update(deltaTime);
        if (timer.isTicking()) return;

        for (GameObject object : HealthObj){
            if (TARGET.contains(object.getClass()) &&
                    property.isTouch(object.getProperty())){
                boolean damaged = object.takeDamage(Damage);
                if (damaged) handleRigid(property);
            }
        }
    }

    // Private Method

    private void handleRigid(Property property){
        if (timer.isPending()){
            property.pauseMovement(AttackRigid);
            timer.start();
        }
    }

}
