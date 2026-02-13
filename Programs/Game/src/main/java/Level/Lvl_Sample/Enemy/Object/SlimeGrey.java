package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Tackle;
import Level.Lvl_Sample.Enemy.Config.SlimeGreyConfig;

import java.util.ArrayList;

public class SlimeGrey extends ImageObject {

    private final int size;
    private Tackle tackle;

    public SlimeGrey(ArrayList<Property> properties) {
        this(properties, 2); // Default size
    }

    public SlimeGrey (ArrayList<Property> properties, int size, Position destination){
        super(SlimeGreyConfig.getProperty(size), ImageLoader.L01_SLIME_GREY);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size);
        property.spawnNearBy(properties , destination);
    }

    public SlimeGrey(ArrayList<Property> properties, int size){
        super(SlimeGreyConfig.getProperty(size), ImageLoader.L01_SLIME_GREY);
        this.size = size;
        this.tackle = SlimeGreyConfig.getTackle(size);
        do{
            property.spawnNearBoundary();
        } while (property.isCollide(properties));
    }

    @Override
    public void update(double deltaTime, Observer observer){
        property.pointTo(observer.getPlayerPosition());
        super.update(deltaTime, observer);
        tackle.update(this.property, observer.getHealthObj(), deltaTime);
    }

    public int getSize(){
        return size;
    }

    public boolean spawnable(){
        return size > 1;
    }
}
