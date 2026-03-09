package Level.Lvl_Sample.Enemy.Interface;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
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
        property.pointTo(observer.getPlayerPosition());

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
