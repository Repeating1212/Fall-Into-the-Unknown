package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.Lvl_Sample.Enemy.Interface.Spawnable;

import java.util.ArrayList;

public class Spawn implements Behaviour{

    private final Timer cooldown;
    private final Spawnable owner;

    private boolean autoActivate = false;
    private boolean isRunning;

    public Spawn(double cooldownDuration, Spawnable owner){
        this.cooldown = new Timer(cooldownDuration);
        this.owner = owner;
    }

    @Override
    public void update(double deltaTime, Observer observer) {
        isRunning = owner.spawnable();
        cooldown.update(deltaTime);
        if (autoActivate) activate(observer);
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }

    @Override
    public boolean isRunning(){
        return isRunning;
    }

    @Override
    public void activate(Observer observer){
        if (cooldown.isDeactive() && ! isRunning){
            owner.setSpawnable(true);
            cooldown.start();
        }
    }

    @Override
    public boolean isCooldown(){
        return cooldown.isTicking();
    };

    @Override
    public void setAutoActivate(boolean autoActivate){
        this.autoActivate = autoActivate;
    }

}
