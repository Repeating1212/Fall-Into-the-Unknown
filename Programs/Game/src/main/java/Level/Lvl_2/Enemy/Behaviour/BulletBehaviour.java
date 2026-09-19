package Level.Lvl_2.Enemy.Behaviour;


import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_2.Enemy.Object.Bullet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BulletBehaviour implements Behaviour {

    private final double DAMAGE;
    private final Bullet bullet;
    private final Property bulletProperty;
    private final List<Class<? extends GameObject>> TARGET = Arrays.asList(
            Player.class
    );

    public BulletBehaviour(double damage, Bullet bullet){
        this.DAMAGE = damage;
        this.bullet = bullet;
        this.bulletProperty = bullet.getProperty();
    }

    public void update(double deltaTime, Observer observer){
        for (GameObject object : observer.getHealthObj()){
            if( isHittable(object) ){
                object.takeDamage(DAMAGE);
                bullet.setDisappear();
            }
        }
    }

    @Override
    public boolean isRunning(){
        return true;
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }

    @Override
    public void activate(Observer observer){
        System.out.println("ERROR, BulletBehaviour called activate");
    }

    @Override
    public boolean isCooldown(){
        return false;
    }

    @Override
    public void setAutoActivate(boolean autoActivate){
        System.out.println("ERROR, BulletBehaviour called setAutoActivate");
    }

    // Private Method

    private boolean isHittable(GameObject object){
        // Object is target + Object touching owner + Object taken damage
        return (TARGET.contains(object.getClass())
                && bulletProperty.isTouch(object.getProperty()));
    }
}
