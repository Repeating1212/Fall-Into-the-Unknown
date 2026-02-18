package Level.Lvl_Sample.Behaviour;

import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;

import java.util.ArrayList;

public interface Behaviour {

    void update(double deltaTime, Observer observer);
    ArrayList<DisplayableObject> getVisual();
    boolean isRunning();
    void activate(Observer observer);
    boolean isCooldown();
    void setAutoActivate(boolean autoActivate);
}
