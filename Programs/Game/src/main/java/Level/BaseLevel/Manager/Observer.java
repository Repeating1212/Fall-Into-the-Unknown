package Level.BaseLevel.Manager;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;


import java.util.ArrayList;

public class Observer {

    private final Player player;
    private final ArrayData<GameObject> gameObj;

    public Observer(Player player, ArrayData<GameObject> gameObjects){
        this.gameObj = gameObjects;
        this.player = player;
    }

    // Get by GameObjects

    public ArrayList<Property> getGameObjPrt() {
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObj.get()) {
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    public ArrayList<GameObject> getHealthObj() {
        ArrayList<GameObject> healthObj = new ArrayList<>(gameObj.get());
        for (GameObject gameObject : gameObj.get()){
            if (gameObject.isHealthNull()){
                healthObj.remove(gameObject);
            }
        }
        return healthObj;
    }

    public GameObject getPlayer(){
        return player;
    }

    public Position getPlayerPosition(){
        return player.getProperty().duplicatePosition();
    }
}
