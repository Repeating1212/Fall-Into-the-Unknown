package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SceneView;

import java.util.ArrayList;

public abstract class LevelManager {

    protected final Player player;
    protected final SceneView sceneView;
    protected final ArrayData<GameObject> gameObj = new ArrayData();
    protected boolean gamePause = false;

    public LevelManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        gameObj.add(player);
    }

    protected void removeDead() {
        ArrayList<GameObject> toRemove = new ArrayList<>();

        for (GameObject gameObject : gameObj.get()) {
            if (!gameObject.isHealthNull() && gameObject.isDead()) {
                toRemove.add(gameObject);
            }
        }

        gameObj.remove(toRemove);
    }

    protected void handleDisplay(double progression){

        ArrayData<DisplayableObject> toDisplay = new ArrayData<>();

        for (GameObject object : gameObj.get()){
            toDisplay.add(object.getRelatedSprite());
        }

        sceneView.updateObjects(toDisplay.get());
        sceneView.updatePlayerHeartView(player.getHealth());
        displaySkillCooldown();
        sceneView.updateBossHealthBar(progression);
    }

    protected void displaySkillCooldown(){
        double[] cooldowns = player.getSkillCooldown();
        for (int i = 0; i < cooldowns.length; i++){
            sceneView.updateSkillCooldowns(i ,cooldowns[i]);
        }
    }

    protected void handleGameCondition() {
        if(winCondition()){
            sceneView.showWinScreen(5);
            gamePause = true;
        }
        else if(loseCondition()) {
            sceneView.showLoseScreen();
            gamePause = true;
        }
    }
    
    protected abstract boolean loseCondition();
    
    protected abstract boolean winCondition();
}
