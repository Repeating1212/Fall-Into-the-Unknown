package Level.BaseLevel.Manager;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Behaviour.Fission;

import java.util.ArrayList;

public abstract class Wave implements Updater {

    protected final Player player;
    protected final SceneView sceneView;
    protected ArrayData<GameObject> gameObj = new ArrayData<>();

    protected final int TOTAL_ROUND;
    protected Timer roundTimer = new Timer(0); // Prevent null
    protected int currentRound = 0;

    // Slime Spawn
    private final Fission fission = new Fission();

    public Wave(SceneView sceneView, Player player, int totalRound){
        this.player = player;
        this.sceneView = sceneView;
        this.TOTAL_ROUND = totalRound;
        gameObj.add(player);
    }

    public void updateObjects(double deltaTime){
        Observer observer = new Observer(player, gameObj);

        if (currentRound < TOTAL_ROUND &&
                (roundTimer.isEnd() || gameObj.length() == 1)){
            spawnEnemies();
            roundTimer = new Timer(getRoundDuration(), true);

            currentRound++;
        }

        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }

        roundTimer.update(deltaTime);
        gameObj = fission.spawnSlime(gameObj.copyOf());
        handleDisplay(getDisplayProgress());
        removeObject();
    }

    public boolean isLose(){
        return player.toRemove();
    }

    public void setGameObj(ArrayData<GameObject> gameObj){
        this.gameObj = gameObj;
    }

    public ArrayData<GameObject> getGameObj(){
        return gameObj;
    }

    // Abstract Method

    public abstract boolean isComplete();

    protected abstract void spawnEnemies();

    protected abstract int getRoundDuration();

    // Helper method

    protected ArrayList<Property> getProperties(ArrayData<GameObject> gameObjects){
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObjects.get()){
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    protected double getRoundProgression(){
        return (double) currentRound / TOTAL_ROUND;
    }

    protected double getDisplayProgress(){ return roundTimer.getCooldownPercentage(); }


    // Private Method

    private void handleDisplay(double progression){

        ArrayData<DisplayableObject> toDisplay = new ArrayData<>();

        for (GameObject object : gameObj.get()){
            toDisplay.add(object.getRelatedSprite());
        }

        sceneView.updateObjects(toDisplay.get());
        sceneView.updatePlayerHeartView(player.getHealth());
        sceneView.updateSkillCooldowns(player.getSkillCooldown());
        sceneView.updateBossHealthBar(progression);
    }

    private void removeObject() {
        ArrayList<GameObject> toRemove = new ArrayList<>();

        for (GameObject gameObject : gameObj.get()) {
            if (gameObject.toRemove()) {
                toRemove.add(gameObject);
            }
        }

        gameObj.remove(toRemove);
    }
}
