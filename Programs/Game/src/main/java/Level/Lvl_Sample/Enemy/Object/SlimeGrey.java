package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;
import Level.Lvl_Sample.Enemy.Interface.Slime;

import java.util.ArrayList;
import java.util.Random;

public class SlimeGrey extends Slime {

    private Behaviour tackle;
    private boolean isSpawned = false;

    public SlimeGrey(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeGrey(ArrayList<Property> properties, int size){
        super(size
                ,SlimeGreyConfig.getProperty(size),
                ImageLoader.L01_SLIME_GREY,
                SlimeGreyConfig.DEAD_DURATION);
        this.tackle = SlimeGreyConfig.getTackle(size, this.property);
        property.spawnNearBoundary(properties);
    }

    @Override
    public void updateAlive(double deltaTime, Observer observer){
        property.pointTo(observer.getPlayerPosition());
        tackle.update(deltaTime, observer);
        super.updateAlive(deltaTime, observer);
    }

    // Smile Interface

    @Override
    public boolean spawnable(){
        return (SIZE > 1 &&
                ! isSpawned &&
                deadAnimation.isEnd());
    }

    @Override
    public ArrayList<Slime> getChildren(ArrayList<Property> properties){
        Random random = new Random();

        ArrayList<Slime> children = new ArrayList<>();
        int spawnNum = random.nextInt(SlimeGreyConfig.MIN_SPAWN, SlimeGreyConfig.MAX_SPAWN);
        for (int i = 0; i < spawnNum ; i ++){
            children.add(super.randomSpawn(properties, property.duplicatePosition()));
        }
        return children;
    }

}
