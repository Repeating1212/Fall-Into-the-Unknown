package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Property;

import Level.BaseLevel.View.AttackVisualize.RectangleAttackVisual;

import java.util.ArrayList;

public class Rush {

    private final double RANGE;
    private final double SPEED_INCREMENT;
    private final double DAMAGE;

    private Timer cooldown;
    private Timer rigid;
    private RectangleAttackVisual attackVisual;

    private boolean pendingRush = false;
    private double currentRush;
    private double totalRush;

    private Property owner;

    public Rush(double range, double cooldown, double damage, double speedIncrement, Property owner, double rushRigid){
        this.RANGE = range;
        this.SPEED_INCREMENT = speedIncrement;
        this.DAMAGE = damage;

        this.cooldown = new Timer(cooldown);
        this.rigid = new Timer(rushRigid);
        this.owner = owner;
        this.attackVisual = new RectangleAttackVisual(range, owner.getHeight());
    }

    public void update(double deltaTime, GameObject player){
        cooldown.update(deltaTime);
        rigid.update(deltaTime);


        handleActivate();
        handleRigid();
        handleRush(player);
        handleComplete();
    }

    public RectangleAttackVisual getAttackVisual(){
        return attackVisual;
    }

    public boolean isRunning(){
        return (rigid.isTicking() || cooldown.isPending());
    }

    // Private Method

    private void handleActivate(){
        if (rigid.isDeactive() && cooldown.isDeactive() && !pendingRush){
            rigid.start();
            owner.setPauseMovement(true);
            owner.setDirectable(false);
            pendingRush = true;

            attackVisual.activate(owner.getCenterPos(), owner.getDirection());
        }
    }

    private void handleRigid(){
        if (rigid.isEnd() && cooldown.isDeactive() && pendingRush ){
            pendingRush = false;
            cooldown.setPending();

            owner.setPauseMovement(false);
            owner.speedMultiply(SPEED_INCREMENT);

            currentRush = 0;
            totalRush = (RANGE / owner.getSpeed());
        }
    }

    private void handleRush(GameObject player){
        if (!pendingRush && cooldown.isPending() &&
                currentRush < totalRush && rigid.isDeactive()){

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
