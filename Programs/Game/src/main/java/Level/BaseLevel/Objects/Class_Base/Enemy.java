package Level.BaseLevel.Objects.Class_Base;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import javafx.scene.image.Image;

public abstract class Enemy extends ImageObject {

    protected Behaviour[] behaviours;

    public Enemy(Property property, Image image, double deadDuration) {
        super(property, image, deadDuration);
        behaviours = getBehaviours();
    }

    protected abstract Behaviour[] getBehaviours();

    // Override Method

    @Override
    public void updateAlive(double deltaTime, Observer observer) {

        if (! property.isCollide(observer.getGameObjPrt())){
            for (Behaviour behaviour: behaviours){
                behaviour.update(deltaTime, observer);
            }
        }

        super.updateAlive(deltaTime, observer);
    }

    @Override
    public ArrayData<DisplayableObject> reloadDisplay(){
        ArrayData<DisplayableObject> display = super.reloadDisplay();
        for (Behaviour behaviour: behaviours){
            if(behaviour.isRunning() && deadAnimation.isDeactive()){
                display.add(behaviour.getVisual());
            }
        }
        return display;
    }
}
