package Lvl_Sample.Managers;

import Lvl_Sample.Data.Properties.Property;
import Lvl_Sample.Objects.Base_Class.GameObject;
import Lvl_Sample.Objects.Concrete_Class.Player;
import Lvl_Sample.View.SceneView;


import java.util.ArrayList;

public class Observer {

    private Property player;
    private final SceneView sceneView;
    private final Level level;

    public Observer(Level level, SceneView sceneView){
        this.level = level;
        this.sceneView = sceneView;
    }

    protected void setPlayer(Player player){
        this.player = player.getProperty();
    }

    // Get by GameObjects

    public ArrayList<Property> getObjectProperties() {
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : level.getGameObjects()) {
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    public ArrayList<GameObject> getLivingEntities() {
        return level.getHealthObj();
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
}
