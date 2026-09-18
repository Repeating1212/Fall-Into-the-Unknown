package Level.Lvl_1.Behaviour;

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
    private boolean autoActivate = true;

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
        if (autoActivate) activate(observer);
    }

    @Override
    public boolean isRunning(){
        return cooldown.isTicking();
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }

    @Override
    public void activate(Observer observer){
        for (GameObject object : observer.getHealthObj()){
            if( isHittable(object) ){
                cooldown.start();
                owner.pauseMovement(RIGID_DURATION);
            }
        }
    }

    @Override
    public boolean isCooldown(){
        return cooldown.isTicking();
    }

    @Override
    public void setAutoActivate(boolean autoActivate){
        this.autoActivate = autoActivate;
    }

    // Private Method

    private boolean isHittable(GameObject object){
        // Object is target + Object touching owner + Object taken damage
        return (TARGET.contains(object.getClass()) &&
                owner.isTouch(object.getProperty())&&
                object.takeDamage(DAMAGE));
    }
}
