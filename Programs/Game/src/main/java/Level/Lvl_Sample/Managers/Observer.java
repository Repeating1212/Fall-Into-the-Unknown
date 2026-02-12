package Level.Lvl_Sample.Managers;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;


import java.util.ArrayList;

public class Observer {

    private Player player;
    private ArrayData<GameObject> gameObj;
    private ArrayData<DisplayableObject> displayObj;

    public Observer(){
    }

    protected void setGameObj(ArrayData<GameObject> gameObj){
        this.gameObj = gameObj;
    }

    protected void setDisplayObj(ArrayData<DisplayableObject> displayObj) {
        this.displayObj = displayObj;
    }

    protected void setPlayer(Player player){
        this.player = player;
    }

    // Get by GameObjects

    public ArrayList<Property> getObjectProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObj.get()) {
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    public ArrayList<GameObject> getLivingEntities() {
        ArrayList<GameObject> healthObj = new ArrayList<>(gameObj.get());
        for (GameObject gameObject : gameObj.get()){
            if (gameObject.isHealthNull()){
                healthObj.remove(gameObject);
            }
        }
        return healthObj;
    }
    
    // Display Related

    public GameObject getPlayer(){
        return player;
    }

    public void addDisplayableObject(DisplayableObject displayableObject){
        if (displayableObject == null) return;
        displayObj.add(displayableObject);
    }

    public void removeDisplayableObject(DisplayableObject displayableObject){
        if (displayableObject == null) return;
        displayObj.remove(displayableObject);
    }
}
