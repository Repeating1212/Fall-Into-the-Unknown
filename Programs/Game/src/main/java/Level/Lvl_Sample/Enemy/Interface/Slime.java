package Level.Lvl_Sample.Enemy.Interface;

import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;
import Level.Lvl_Sample.Enemy.Object.SlimeWhite;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public abstract class Slime extends ImageObject {

    protected final int SIZE;
    protected boolean spawnable = false;
    private final Class<? extends Slime>[] childrenType = new Class[]{
            SlimeGrey.class,
            SlimeWhite.class,
    };

    public Slime(int size, Property property, Image image, double deadDuration){
            super(property, image, deadDuration);
            this.SIZE = size;
    }

    public int getSize(){
        return SIZE;
    }

    public void setSpawned(){
        spawnable = true;
    }

    protected Slime randomSpawn(ArrayList<Property> properties, Position position){
        Random random = new Random();
        int index = random.nextInt(childrenType.length);
        int size = SIZE -1;

        Slime slime;

        if (childrenType[index] == SlimeGrey.class) {
            slime = new SlimeGrey(properties, size);
        } else if (childrenType[index] == SlimeWhite.class) {
            slime = new SlimeWhite(properties, size);
        } else {
            slime = new SlimeGrey(properties, size);
        }
        slime.getProperty().spawnNearBy(properties, position);
        return slime;
    }

    public abstract boolean spawnable();

    public abstract ArrayList<Slime> getChildren(ArrayList<Property> properties);

}
