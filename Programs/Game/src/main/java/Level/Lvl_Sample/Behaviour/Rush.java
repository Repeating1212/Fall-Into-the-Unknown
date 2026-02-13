package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Properties.Property;

public class Rush {

    private final double RANGE;
    private final double SPEED_INCREMENT;
    private Timer cooldown;

    private double currentRush;
    private double totalRush;

    private Property owner;

    public Rush(double range, double cooldown, double speedIncrement, Property owner){
        this.RANGE = range;
        this.SPEED_INCREMENT = speedIncrement;
        this.cooldown = new Timer(cooldown);
        this.owner = owner;
    }

    public void activate(){
        if (cooldown.isDeactive()){
            cooldown.setPending();
            owner.speedMultiply(SPEED_INCREMENT);
            currentRush = 0;
            totalRush = (RANGE / owner.getSpeed());
            owner.setDirectable(false);
        }
    }

    public void update(double deltaTime){
        cooldown.update(deltaTime);
        currentRush ++;
        handleComplete();
        handleActivate();
    }

    // Private Method

    private void handleComplete(){
        if (cooldown.isPending() && currentRush >= totalRush){
            cooldown.start();
            owner.speedDivide(SPEED_INCREMENT);
            owner.setDirectable(true);
            System.out.println(owner.getSpeed());
        }
    }

    private void handleActivate(){
        if (cooldown.isDeactive()){
            activate();
        }
    }
}
