package Level.Lvl_Sample.Enemy.Object;

import Data.DataClass.ArrayData;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Behaviour.Rush;
import Level.Lvl_Sample.Enemy.Config.SlimeWhiteConfig;

import java.util.ArrayList;

public class SlimeWhite extends ImageObject {

    private final int size;

    private Behaviour[] behaviours;

    public SlimeWhite(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeWhite(ArrayList<Property> properties, int size){
        super(SlimeWhiteConfig.getProperty(size),
                ImageLoader.L01_SLIME_WHITE,
                SlimeWhiteConfig.DEAD_DURATION);
        this.size = size;
        behaviours = new Behaviour[]{
                SlimeWhiteConfig.getRush(this.property),
                SlimeWhiteConfig.getTackle(this.property, size)
        };
        property.spawnNearBoundary(properties);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        super.update(deltaTime, observer);

        if (isAlive()){
            property.pointTo(observer.getPlayerPosition());
            for (Behaviour behaviour: behaviours){
                behaviour.update(deltaTime, observer);
            }
        }
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

    public int getSize(){
        return size;
    }
}
