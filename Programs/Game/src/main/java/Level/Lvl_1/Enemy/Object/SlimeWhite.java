package Level.Lvl_1.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Enemy.Config.SlimeWhiteConfig;
import Level.Lvl_1.Enemy.Interface.Slime;

import java.util.ArrayList;

public class SlimeWhite extends Slime {

    private final boolean SPAWNABLE = false; // White slime cannot spawn

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
        property.spawnNearBoundary(properties);
        behaviours = getBehaviours();
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                        SlimeWhiteConfig.getRush(this.property, SIZE),
                        SlimeWhiteConfig.getTackle(this.property, SIZE)
                };
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
