package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;

import java.util.ArrayList;
import java.util.Random;

public class Wave1  extends LevelManager implements Updater{

    private final int TOTAL_WAVE = 4;
    private final int WAVE_DURATION = 5;

    private int currentWave = 0;
    private Timer waveTimer = new Timer(WAVE_DURATION);


    public Wave1(SceneView sceneView, Player player){
        super(sceneView, player);
    }

    public void updateObjects(double deltaTime){
        Observer observer = new Observer(player, gameObj);
        if (gamePause) return;

        if (waveTimer.isEnd() && currentWave < TOTAL_WAVE){
            waveTimer.start();
            spawnEnemies();
            currentWave ++;
        }

        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }

        waveTimer.update(deltaTime);

        removeDead();
        handleDisplay(1 - (double) currentWave / TOTAL_WAVE);
        handleGameCondition();
    }

    // Override Method

    @Override
    protected boolean winCondition(){
        return (currentWave >= TOTAL_WAVE && waveTimer.isEnd());
    }

    @Override
    protected boolean loseCondition(){
        return player.isDead();
    }

    // Private Method

    private void spawnEnemies(){
        Random random = new Random();
        int num =  random.nextInt(2,5);
        for (int i = 0; i < num; i++){
            ArrayList<Property> properties = getProperties(gameObj);
            Rat rat = new Rat(properties);
            gameObj.add(rat);
        }
    }

    private ArrayList<Property> getProperties(ArrayData<GameObject> gameObjects){
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObjects.get()){
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

}
