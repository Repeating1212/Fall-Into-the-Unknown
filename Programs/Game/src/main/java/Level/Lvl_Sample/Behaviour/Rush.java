package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Properties.Property;

import Level.BaseLevel.View.AttackVisualize.RectangleAttackVisual;

public class Rush {

    private final double RANGE;
    private final double SPEED_INCREMENT;

    private Timer cooldown;
    private RectangleAttackVisual attackVisual;
    private double currentRush;
    private double totalRush;

    private Property owner;

    public Rush(double range, double cooldown, double speedIncrement, Property owner){
        this.RANGE = range;
        this.SPEED_INCREMENT = speedIncrement;
        this.cooldown = new Timer(cooldown);
        this.owner = owner;
        this.attackVisual = new RectangleAttackVisual(range, owner.getHeight());
    }

    public void activate(){
        if (cooldown.isDeactive()){
            cooldown.setPending();
            owner.speedMultiply(SPEED_INCREMENT);
            currentRush = 0;
            totalRush = (RANGE / owner.getSpeed());
            owner.setDirectable(false);
            attackVisual.activate(owner.getCenterPos(), owner.getDirection());
        }
    }

    public void update(double deltaTime){
        cooldown.update(deltaTime);
        currentRush ++;
        handleComplete();
        handleActivate();
    }

    public RectangleAttackVisual getAttackVisual(){
        return attackVisual;
    }

    public boolean isRunning(){
        return (cooldown.isPending());
    }


    // Private Method

    private void handleComplete(){
        if (cooldown.isPending() && currentRush >= totalRush){
            cooldown.start();
            owner.speedDivide(SPEED_INCREMENT);
            owner.setDirectable(true);
        }
    }

    private void handleActivate(){
        if (cooldown.isDeactive()){
            activate();
        }
    }
}
