package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;
import Level.Lvl_Sample.Enemy.Interface.Slime;

import java.util.ArrayList;
import java.util.Random;

public class SlimeGrey extends Slime {


    public SlimeGrey(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    // Default slime
    public SlimeGrey(ArrayList<Property> properties, int size){
        super(size
                ,SlimeGreyConfig.getProperty(size),
                ImageLoader.L01_SLIME_GREY,
                SlimeGreyConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
        behaviours = getBehaviours();
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                SlimeGreyConfig.getTackle(SIZE, this.property)
        };
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
            children.add(super.randomSpawn(properties, property.duplicatePosition(), SIZE -1));
        }
        return children;
    }
}
