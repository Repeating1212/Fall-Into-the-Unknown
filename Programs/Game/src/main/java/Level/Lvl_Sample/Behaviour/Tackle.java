package Level.Lvl_Sample.Behaviour;

import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Data.DataClass.Timer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tackle implements Behaviour{

    private final double Damage;
    private final double AttackRigid;
    private final Timer rigidTimer;
    private final Property owner;

    private final List<Class<? extends GameObject>> TARGET = Arrays.asList(
            Player.class
    );

    public Tackle(double damage, double attackRigid, Property owner){
        this.Damage = damage;
        this.AttackRigid = attackRigid;
        this.rigidTimer = new Timer(attackRigid);
        this.owner = owner;
    }

    public void update(double deltaTime, Observer observer){

        rigidTimer.update(deltaTime);
        if (rigidTimer.isTicking()) return;

        for (GameObject object : observer.getHealthObj()){
            if (TARGET.contains(object.getClass()) &&
                    owner.isTouch(object.getProperty())){
                boolean damaged = object.takeDamage(Damage);
                if (damaged) handleRigid(owner);
            }
        }
    }

    @Override
    public boolean isRunning(){
        return rigidTimer.isTicking();
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }
    // Private Method

    private void handleRigid(Property property){
        if (rigidTimer.isPending()){
            property.pauseMovement(AttackRigid);
            rigidTimer.start();
        }
    }

}
