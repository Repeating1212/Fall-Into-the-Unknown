package Level.Lvl_Sample.Enemy.Object;

import Data.DataClass.ArrayData;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Enemy.Config.SlimeWhiteConfig;
import Level.Lvl_Sample.Enemy.Interface.Slime;

import java.util.ArrayList;

public class SlimeWhite extends Slime {

    private final boolean SPAWNABLE = false; // White slime cannot spawn
    private Behaviour[] behaviours;

    public SlimeWhite(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeWhite(ArrayList<Property> properties, int size){
        super(size,
                SlimeWhiteConfig.getProperty(size),
                ImageLoader.L01_SLIME_WHITE,
                SlimeWhiteConfig.DEAD_DURATION
        );

        behaviours = new Behaviour[]{
                SlimeWhiteConfig.getRush(this.property, size),
                SlimeWhiteConfig.getTackle(this.property, size)
        };
        property.spawnNearBoundary(properties);
    }

    @Override
    public void updateAlive(double deltaTime, Observer observer){
        property.pointTo(observer.getPlayerPosition());
        for (Behaviour behaviour: behaviours){
            behaviour.update(deltaTime, observer);
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

    // Slime Interface

    @Override
    public boolean spawnable(){
        return SPAWNABLE;
    }

    @Override
    public ArrayList<Slime> getChildren(ArrayList<Property> properties) {
        return new ArrayList<>();
    }
}
