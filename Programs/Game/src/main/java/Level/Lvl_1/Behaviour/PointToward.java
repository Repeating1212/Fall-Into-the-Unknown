package Level.Lvl_1.Behaviour;

import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;

import java.util.ArrayList;

public class PointToward implements Behaviour{

    private final Property owner;

    public PointToward(Property owner){
        this.owner = owner;
    }

    public void update(double deltaTime, Observer observer){
        owner.pointTo(observer.getPlayerPosition());
    };

    public ArrayList<DisplayableObject> getVisual(){
        return new ArrayList<>();
    }

    public boolean isRunning(){
        return true;
    }
    public void activate(Observer observer){
        System.out.println("ERROR, PointTowards Called activate Function");
    }

    public boolean isCooldown(){
        return false;
    };

    public void setAutoActivate(boolean autoActivate){
        System.out.println("ERROR, PointTowards Called autoActivate Function");
    };
}
