package Level.Lvl_1.Behaviour;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Enemy.Interface.Slime;

import java.util.ArrayList;

public class Fission{

    public ArrayData<GameObject> spawnSlime(ArrayData<GameObject> gameObj){
        ArrayData<Slime> slimes = getSlime(gameObj);

        for (Slime parent : slimes.get()){
            if (parent.spawnable()){
                ArrayList<Slime> children = parent.getChildren(getProperties(gameObj));
                parent.setSpawned();
                gameObj.add(new ArrayList<GameObject> (children));
            }
        }
        return gameObj;
    }

    // Private Method

    private ArrayData<Slime> getSlime(ArrayData<GameObject> gameObj){
        ArrayData<Slime> returnArray = new ArrayData<>();
        for (GameObject object : gameObj.get()){
            if (object instanceof Slime){
                returnArray.add((Slime) object);
            }
        }
        return returnArray;
    }

    private ArrayList<Property> getProperties(ArrayData<GameObject> gameObjects){
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObjects.get()){
            properties.add(gameObject.getProperty());
        }
        return properties;
    }
}
