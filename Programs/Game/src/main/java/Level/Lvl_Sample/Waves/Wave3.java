package Level.Lvl_Sample.Waves;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;
import Level.Lvl_Sample.Enemy.Object.SlimeKing;
import Level.Lvl_Sample.Enemy.Object.SlimeWhite;

import java.util.ArrayList;
import java.util.Random;

public class Wave3 extends Wave {

    private static final int TOTAL_WAVE = 1;
    private final SlimeKing boss;
    private Timer waveTimer2 = new Timer(3, true); // Won't spawn at start

    public Wave3(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
        this.boss = new SlimeKing(getProperties(gameObj));
    }

    @Override
    public void updateObjects(double deltaTime){
        waveTimer2.update(deltaTime);
        if (waveTimer2.isEnd()){
            Random random = new Random();
            int duration = random.nextInt(3, 7);
            waveTimer2 = new Timer(duration, true);
            spawnEnemy2();
        }
        super.updateObjects(deltaTime);
    }

    @Override
    public boolean isComplete(){
        return gameObj.length() == 1;
    }

    @Override
    protected void spawnEnemies() {
        gameObj.add(boss);
    }

    @Override
    protected double getWaveProgression(){
        return boss.getHealthPercentage();
    }

    // Private Method
    private void spawnEnemy2(){
        Random random = new Random();
        int num =  random.nextInt(1,3);
        for (int i = 0; i < num; i++){
            ArrayList<Property> properties = getProperties(gameObj);
            gameObj.add(new Rat(properties));
        }
    }

}
