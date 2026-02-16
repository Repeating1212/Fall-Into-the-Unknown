package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Property;

import Level.BaseLevel.View.AttackVisualize.RectangleAttackVisual;

import java.util.ArrayList;
import java.util.Objects;

public class Rush implements Behaviour{

    private final double RANGE;
    private final double SPEED_INCREMENT;
    private final double DAMAGE;

    private Timer cooldown;
    private Timer rigid;

    private RectangleAttackVisual attackVisual;
    private ArrayList<DisplayableObject> visualObjects = new ArrayList<>();

    private boolean pendingRush = false;
    private double currentRush;
    private double totalRush;

    private Property owner;
    private boolean autoActivate = true;

    public Rush(double range, double cooldown, double damage, double speedIncrement, Property owner, double rushRigid){
        this.RANGE = range;
        this.SPEED_INCREMENT = speedIncrement;
        this.DAMAGE = damage;

        this.cooldown = new Timer(cooldown);
        this.rigid = new Timer(rushRigid);
        this.owner = owner;
        this.attackVisual = new RectangleAttackVisual(range, owner.getHeight());
        visualObjects.add(attackVisual);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        cooldown.update(deltaTime);
        rigid.update(deltaTime);

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
        if (rigid.isDeactive() && cooldown.isDeactive() && !pendingRush){
            rigid.start();
            owner.pauseMovement(rigid.getDuration());
            owner.setDirectable(false);
            pendingRush = true;
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
        if (rigid.isEnd() && cooldown.isDeactive() && pendingRush ){
            pendingRush = false;
            cooldown.setPending();

            owner.speedMultiply(SPEED_INCREMENT);

            currentRush = 0;
            totalRush = (RANGE / owner.getSpeed());
        }
    }

    private void handleRush(GameObject player){
        if (!pendingRush && cooldown.isPending()){
            currentRush ++;
            if (owner.isTouch(player.getProperty())){
                player.takeDamage(DAMAGE);
            }
        }
    }

    private void handleComplete(){
        if (!pendingRush && cooldown.isPending() &&
                currentRush >= totalRush && rigid.isDeactive()){

            cooldown.start();
            owner.speedDivide(SPEED_INCREMENT);
            owner.setDirectable(true);
        }
    }
}
