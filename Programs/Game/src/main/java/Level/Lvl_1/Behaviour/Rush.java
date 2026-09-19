package Level.Lvl_1.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Property;

import Level.BaseLevel.View.AttackVisualize.RectangleAttackVisual;

import java.util.ArrayList;

public class Rush implements Behaviour{

    private final double RANGE;
    private final double SPEED_INCREMENT;
    private final double DAMAGE;

    private Timer cooldown;
    private Timer rigid;
    private Timer rushTimer;

    private RectangleAttackVisual attackVisual;
    private ArrayList<DisplayableObject> visualObjects = new ArrayList<>();

    private Property owner;
    private boolean autoActivate = true;

    public Rush(double range, double cooldown, double damage, double speedIncrement, Property owner, double rushRigid){
        this.RANGE = range;
        this.SPEED_INCREMENT = speedIncrement;
        this.DAMAGE = damage;

        this.rigid = new Timer(rushRigid);
        this.rushTimer = new Timer(RANGE / (owner.getBaseSpeed() * SPEED_INCREMENT));
        this.cooldown = new Timer(cooldown);

        this.owner = owner;
        this.attackVisual = new RectangleAttackVisual(range, owner.getHeight());
        visualObjects.add(attackVisual);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        rigid.update(deltaTime);
        rushTimer.update(deltaTime);
        cooldown.update(deltaTime);

        if (autoActivate) activate(observer);
        handleRigid();
        handleRush(observer.getPlayer());
        handleComplete();
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return visualObjects;
    }

    @Override
    public boolean isRunning(){
        return (rigid.isTicking() || cooldown.isPending());
    }

    @Override
    public void activate(Observer observer){
        if (rigid.isDeactive() && cooldown.isDeactive() && rushTimer.isDeactive()){
            rigid.start();
            rushTimer.setPending();
            owner.pauseMovement(rigid.getDuration());
            owner.pauseDirect(rigid.getDuration() + rushTimer.getDuration());
            attackVisual.activate(owner.getCenterPos(), owner.getDirection());
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

    private void handleRigid(){
        if (rigid.isEnd() && rushTimer.isPending() ){
            rushTimer.start();
            cooldown.setPending();

            double duration = rushTimer.getDuration();
            owner.setSpeedMultiply(duration, SPEED_INCREMENT);
            owner.pauseDirect(duration);
        }
    }

    private void handleRush(GameObject player){
        if (rushTimer.isTicking()){
            if (owner.isTouch(player.getProperty())){
                player.takeDamage(DAMAGE);
            }
        }
    }

    private void handleComplete(){
        if (rushTimer.isEnd() && cooldown.isPending()){
            cooldown.start();
        }
    }
}
