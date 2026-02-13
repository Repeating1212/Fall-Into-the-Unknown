package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;

import java.util.ArrayList;
import java.util.Random;

public class Wave2 extends Wave implements Updater{

    private final int TOTAL_WAVE = 2;
    private final int WAVE_DURATION = 5;

    private ArrayData<SlimeGrey> slimeGreys = new ArrayData<>();
    private int currentWave = 0;
    private Timer waveTimer = new Timer(WAVE_DURATION);
    private double progress;


    public Wave2(SceneView sceneView, Player player){
        super(sceneView, player);
    }

    public void updateObjects(double deltaTime){
        Observer observer = new Observer(player, gameObj);

        if (waveTimer.isEnd() && currentWave < TOTAL_WAVE){
            waveTimer.start();
            spawnEnemies();
            currentWave ++;
            progress = (double) currentWave / TOTAL_WAVE;
        }

        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }

        waveTimer.update(deltaTime);
        spawnSlime();


        handleDisplay(1 - progress);
        removeObject(); // Always last method
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  player.isAlive() &&
                gameObj.length() == 1 &&
                progress >= 1;
    }

    @Override
    protected void removeObject(){
        ArrayList<GameObject> toRemove = new ArrayList<>();

        for (GameObject gameObject : gameObj.get()) {
            if (gameObject.toRemove()) {
                toRemove.add(gameObject);
                if (gameObject.getClass() == SlimeGrey.class){
                    slimeGreys.remove((SlimeGrey) gameObject);
                }
            }
        }
        gameObj.remove(toRemove);
    }

    // Private Method

    private void spawnEnemies(){
        Random random = new Random();
        int num =  random.nextInt(2,5);
        for (int i = 0; i < num; i++){
            SlimeGrey slimeGrey = new SlimeGrey(getProperties(gameObj));
            slimeGreys.add(slimeGrey);
            gameObj.add(slimeGrey);
        }
    }

    private ArrayList<Property> getProperties(ArrayData<GameObject> gameObjects){
        ArrayList<Property> properties = new ArrayList<>();
        for (GameObject gameObject : gameObjects.get()){
            properties.add(gameObject.getProperty());
        }
        return properties;
    }

    private void spawnSlime(){
        ArrayData<SlimeGrey> temp = new ArrayData<>();
        temp.add(slimeGreys.get());

        for (SlimeGrey parent : temp.get()){
            if (parent.spawnable()){
                for (int i = 0; i < 3; i ++){
                    SlimeGrey child = new SlimeGrey(getProperties(gameObj), parent.getSize() - 1, parent.duplicatePosition());
                    slimeGreys.add(child);
                    gameObj.add(child);
                    parent.setSpawned();
                }
            }

        }
    }
}
