package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;

import java.util.ArrayList;
import java.util.Random;

public class SlimeGrey extends ImageObject {

    private final int size;

    private Behaviour tackle;
    private boolean isSpawned = false;

    public SlimeGrey(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeGrey(ArrayList<Property> properties, int size){
        super(SlimeGreyConfig.getProperty(size),
                ImageLoader.L01_SLIME_GREY,
                SlimeGreyConfig.DEAD_DURATION);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size, this.property);
        property.spawnNearBoundary(properties);
    }

    // Spawned slime
    public SlimeGrey (ArrayList<Property> properties, int size, Position destination){
        super(SlimeGreyConfig.getProperty(size),
                ImageLoader.L01_SLIME_GREY,
                SlimeGreyConfig.DEAD_DURATION);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size, this.property);
        property.spawnNearBy(properties , destination);
    }

    @Override
    public void update(double deltaTime, Observer observer){
        super.update(deltaTime, observer);

        if (isAlive()){
            property.pointTo(observer.getPlayerPosition());
            tackle.update(deltaTime, observer);
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

    public int getSpawnNum(){
        Random random = new Random();
        return random.nextInt(SlimeGreyConfig.MIN_SPAWN, SlimeGreyConfig.MAX_SPAWN);
    }
}
