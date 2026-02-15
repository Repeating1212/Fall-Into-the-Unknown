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

    private final double DAMAGE;
    private final double RIGID_DURATION;
    private final Timer cooldown;
    private final Property owner;

    private final List<Class<? extends GameObject>> TARGET = Arrays.asList(
            Player.class
    );

    public Tackle(double damage, double attackRigid, Property owner){
        this.DAMAGE = damage;
        this.RIGID_DURATION = attackRigid;
        this.cooldown = new Timer(attackRigid);
        this.owner = owner;
    }

    public void update(double deltaTime, Observer observer){

        cooldown.update(deltaTime);
        if (cooldown.isTicking()) return;

        for (GameObject object : observer.getHealthObj()){
            if( isActivate(object) ){
                cooldown.start();
                owner.pauseMovement(RIGID_DURATION);
            }
        }
    }

    @Override
    public boolean isRunning(){
        return cooldown.isTicking();
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }

    // Private Method

    private boolean isActivate(GameObject object){
        // Object is target + Object touching owner + Object taken damage
        return (TARGET.contains(object.getClass()) &&
                owner.isTouch(object.getProperty())&&
                object.takeDamage(DAMAGE));
    }
}
