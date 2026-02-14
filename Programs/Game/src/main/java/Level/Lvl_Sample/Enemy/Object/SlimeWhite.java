package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Rush;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;
import Level.Lvl_Sample.Enemy.Config.SlimeWhiteConfig;

import java.util.ArrayList;

public class SlimeWhite extends ImageObject {

    private final int size;

    private boolean isSpawned = false;
    private Rush rush;

    public SlimeWhite(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeWhite(ArrayList<Property> properties, int size){
        super(SlimeWhiteConfig.getProperty(size),
                ImageLoader.L01_SLIME_WHITE,
                SlimeWhiteConfig.DEAD_DURATION);
        this.size = size;
        this.rush = SlimeWhiteConfig.getRush(this.property);
        property.spawnNearBoundary(properties);
    }

    // Spawned slime
    public SlimeWhite(ArrayList<Property> properties, int size, Position destination){
        super(SlimeGreyConfig.getProperty(size),
                ImageLoader.L01_SLIME_WHITE,
                SlimeWhiteConfig.DEAD_DURATION);
        this.size = size;
        this.rush = SlimeWhiteConfig.getRush(this.property);
        property.spawnNearBy(properties , destination);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        super.update(deltaTime, observer);

        if (isAlive()){
            property.pointTo(observer.getPlayerPosition());
            rush.update(deltaTime, observer.getPlayer());
            if(rush.isRunning()){
                relatedDisplay.add(rush.getAttackVisual());
            } else {
                relatedDisplay.remove(rush.getAttackVisual());
            }
        }


    }

    public int getSize(){
        return size;
    }

    public boolean spawnable(){
        return (size > 1 &&
                ! isSpawned &&
                deadAnimation.isEnd());
    }

    public void setSpawned(){
        isSpawned = true;
    }
}
