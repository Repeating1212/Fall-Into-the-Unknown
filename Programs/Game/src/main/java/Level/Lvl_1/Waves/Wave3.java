package Level.Lvl_1.Waves;

import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Enemy.Object.Rat;
import Level.Lvl_1.Enemy.Object.SlimeKing;

import java.util.ArrayList;
import java.util.Random;

public class Wave3 extends Wave {

    private static final int TOTAL_ROUND = 1;

    private final SlimeKing boss;
    private Timer ratSpawnTimer = new Timer(3, true); // Won't spawn at start

    public Wave3(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_ROUND);
        this.boss = new SlimeKing(getProperties(gameObj));
    }

    @Override
    public void updateObjects(double deltaTime){
        ratSpawnTimer.update(deltaTime);
        if (ratSpawnTimer.isEnd()){
            Random random = new Random();
            int duration = random.nextInt(3, 7);
            ratSpawnTimer = new Timer(duration, true);
            spawnRat();
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
    protected double getDisplayProgress(){
        return boss.getHealthPercentage();
    }

    @Override
    protected int getRoundDuration(){
        return Math.max(8, gameObj.length());
    }

    // Private Method
    private void spawnRat(){
        if (boss.isDead()) return;
        int spawnNum = 2;
        for (int i = 0; i < spawnNum; i++){
            ArrayList<Property> properties = getProperties(gameObj);
            gameObj.add(new Rat(properties));
        }
    }

}
