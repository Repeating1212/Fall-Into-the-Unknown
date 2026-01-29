package Level.Managers;

import Level.Data.Properties.Property;
import Level.Objects.Base_Class.GameObject;
import Level.Objects.Concrete_Class.Player;
import Level.View.SceneView;


import java.util.ArrayList;

public class Observer {

    private Property player;
    private final SceneView sceneView;
    private final Level level;

    protected Observer(Level level, SceneView sceneView){
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

    public void updateSkillCooldowns(double[] cooldownPercentage){
        // Only if cooldownPercentage.length = 4
        sceneView.updateSkillCooldowns(cooldownPercentage);
    }

    public void updatePlayerHeartView(int heart){
        sceneView.updatePlayerHeartView(heart);
    }
}
