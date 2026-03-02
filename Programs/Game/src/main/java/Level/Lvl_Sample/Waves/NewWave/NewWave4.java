package Level.Lvl_Sample.Waves.NewWave;

import Data.DataClass.Timer;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;
import Level.Lvl_Sample.Enemy.Object.SlimeKing;
import Level.Lvl_Sample.Waves.Wave;

import java.util.ArrayList;

public class NewWave4 extends Wave {

    private static final int TOTAL_ROUND = 1;

    private final SlimeKing boss;
    private final Timer ratSpawnTimer = new Timer(6, true);

    public NewWave4(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_ROUND);
        this.boss = new SlimeKing(getProperties(gameObj));
    }

    @Override
    public void updateObjects(double deltaTime){
        ratSpawnTimer.update(deltaTime);
        if (ratSpawnTimer.isEnd()){
            ratSpawnTimer.start();
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
        return 10;
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
