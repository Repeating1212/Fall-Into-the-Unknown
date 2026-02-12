package Level.Lvl_Sample.Managers;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SceneView;


import java.util.ArrayList;

public class Observer {

    private Property player;
    private GameObject enemy;
    private ArrayData<GameObject> gameObj;
    private ArrayData<DisplayableObject> displayObj;
    private final SceneView sceneView;

    public Observer(SceneView sceneView){
        this.sceneView = sceneView;
    }

    protected void setGameObj(ArrayData<GameObject> gameObj){
        this.gameObj = gameObj;
    }

    protected void setDisplayObj(ArrayData<DisplayableObject> displayObj) {
        this.displayObj = displayObj;
    }

    protected void setPlayer(Player player){
        this.player = player.getProperty();
    }

    protected void setEnemy(GameObject enemy){
        this.enemy = enemy;
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

    public Property getPlayerProperty() {
        return player;
    }
    
    // Display Related

    public void updateBossHealthBar(double progress){
        sceneView.updateBossHealthBar(progress);
    }

    public void updateSkillCooldowns(int skillID , double cooldownPercentages){
        sceneView.updateSkillCooldowns(skillID ,cooldownPercentages);
    }

    public void updatePlayerHeartView(int heart){
        sceneView.updatePlayerHeartView(heart);
    }

    public GameObject getEnemy(){
        return enemy;
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
