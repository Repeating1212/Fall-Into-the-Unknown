package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;

import java.util.ArrayList;

public abstract class Wave implements Updater{

    protected final Player player;
    protected final SceneView sceneView;
    protected ArrayData<GameObject> gameObj = new ArrayData();

    protected Timer waveTimer = new Timer(0); // Prevent null
    protected final int TOTAL_WAVE;
    protected int currentWave = 0;

    public Wave(SceneView sceneView, Player player, int totalWave){
        this.player = player;
        this.sceneView = sceneView;
        this.TOTAL_WAVE = totalWave;
        gameObj.add(player);
    }

    public void updateObjects(double deltaTime){
        Observer observer = new Observer(player, gameObj);

        if (waveTimer.isEnd() && currentWave < TOTAL_WAVE){
            spawnEnemies();
            waveTimer = new Timer(gameObj.length());
            waveTimer.start();

            currentWave ++;
        }

        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }

        waveTimer.update(deltaTime);
        handleSpawnSlime();
        handleDisplay(1 - ((double) currentWave / TOTAL_WAVE));
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

    // Helper method

    protected ArrayList<Property> getProperties(ArrayData<GameObject> gameObjects){
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObjects.get()){
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    protected double getProgress(){
        return (double) currentWave / TOTAL_WAVE;
    }

    // Private Method

    private void handleSpawnSlime(){
        ArrayData<SlimeGrey> slimeGreys = getSlimeGreys();

        for (SlimeGrey parent : slimeGreys.get()){
            if (parent.spawnable()){
                for (int i = 0; i < parent.getSpawnNum(); i ++){
                    SlimeGrey child = new SlimeGrey(getProperties(gameObj), parent.getSize() - 1, parent.duplicatePosition());
                    gameObj.add(child);
                    parent.setSpawned();
                }
            }

        }
    }


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

    private ArrayData<SlimeGrey> getSlimeGreys(){
        ArrayData<SlimeGrey> returnArray = new ArrayData<>();
        for (GameObject object : gameObj.get()){
            if (object.getClass() == SlimeGrey.class){
                returnArray.add((SlimeGrey) object);
            }
        }
        return returnArray;
    }
}
