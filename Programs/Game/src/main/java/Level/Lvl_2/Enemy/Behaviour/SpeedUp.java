package Level.Lvl_2.Enemy.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;

import java.util.ArrayList;

public class SpeedUp implements Behaviour {

    private final Timer cooldown;
    private final Property owner;
    private boolean autoActivate = false;

    private int speedIncrementValue;
    private double speedIncrementDuration;

    public SpeedUp(double cooldownPeriod, Property owner, int speedIncrementValue, double speedIncrementDuration){
        this.cooldown = new Timer(cooldownPeriod, true);
        this.owner    = owner;
        this.speedIncrementValue = speedIncrementValue;
        this.speedIncrementDuration = speedIncrementDuration;
    }

    @Override
    public void update(double deltaTime, Observer observer){
        cooldown.update(deltaTime);
        if (cooldown.isTicking()) return;
        if (autoActivate) activate(observer);
    }

    @Override
    public void activate(Observer observer){
        if (!owner.status.isPauseMovement()){
            owner.status.setSpeedIncrement(speedIncrementDuration, speedIncrementValue);
        }
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){return new ArrayList<>();}

    @Override
    public boolean isRunning(){
        return cooldown.isTicking();
    }

    @Override
    public boolean isCooldown(){
        return cooldown.isTicking();
    }

    @Override
    public void setAutoActivate(boolean autoActivate){
        this.autoActivate = autoActivate;
    }
}
