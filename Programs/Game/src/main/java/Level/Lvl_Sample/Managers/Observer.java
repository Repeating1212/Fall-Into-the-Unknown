package Level.Lvl_Sample.Managers;

import Level.BaseLevel.Manager.LevelData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SceneView;


import java.util.ArrayList;

public class Observer {

    private Property player;
    private GameObject enemy;
    private final SceneView sceneView;
    private final LevelData levelData;

    public Observer(LevelData levelData, SceneView sceneView){
        this.levelData = levelData;
        this.sceneView = sceneView;
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
        for (GameObject gameObject : levelData.getGameObjects()) {
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    public ArrayList<GameObject> getLivingEntities() {
        return levelData.getHealthObj();
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
        levelData.addDisplay(displayableObject);
    }

    public void removeDisplayableObject(DisplayableObject displayableObject){
        if (displayableObject == null) return;
        levelData.removeDisplay(displayableObject);
    }
}
