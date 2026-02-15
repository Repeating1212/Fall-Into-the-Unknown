package Level.Lvl_Sample.Behaviour;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Enemy.Config.SlimeKingConfig;

import java.util.ArrayList;
import java.util.Random;

public class BossBehaviour implements Behaviour {

    private boolean autoActivate = true;

    private final Behaviour[] behaviours;

    public BossBehaviour(Property owner){
        behaviours = new Behaviour[]{
                SlimeKingConfig.getGroupSlap(owner),
                SlimeKingConfig.getRush(owner)
        };
        for (Behaviour behaviour : behaviours){
            behaviour.setAutoActivate(false);
        }
    }

    public void update(double deltaTime, Observer observer){
        boolean activate = true;
        for (Behaviour behaviour : behaviours){
            if (behaviour.isRunning() || behaviour.isCooldown()){
                activate = false;
            }
        }

        if (activate && autoActivate) {
            Random random = new Random();
            int i = random.nextInt(behaviours.length);
            behaviours[i].activate(observer);
        }

        for (Behaviour behaviour : behaviours){
            behaviour.update(deltaTime, observer);
        }
    }

    @Override
    public ArrayList<DisplayableObject> getVisual(){
        ArrayData<DisplayableObject> visual = new ArrayData<>();
        for (Behaviour behaviour : behaviours){
            if (behaviour.isRunning()){
                visual.add(behaviour.getVisual());
            }
        }
        return visual.get();
    }

    @Override
    public boolean isRunning(){
        for (Behaviour behaviour: behaviours){
            if (behaviour.isRunning()) return true;
        }
        return false;
    }

    @Override
    public void activate(Observer observer){
        for (Behaviour behaviour: behaviours){
            behaviour.activate(observer);
        }
    }

    @Override
    public boolean isCooldown(){
        for (Behaviour behaviour: behaviours){
            if (behaviour.isCooldown()) return true;
        }
        return false;
    }

    @Override
    public void setAutoActivate(boolean autoActivate){
        this.autoActivate = autoActivate;
    }

}
