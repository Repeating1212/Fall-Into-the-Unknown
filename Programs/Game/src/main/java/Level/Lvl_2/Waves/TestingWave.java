package Level.Lvl_2.Waves;

import Level.BaseLevel.Manager.Updater;
import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_2.Enemy.Object.Skeleton;

import java.util.ArrayList;

public class TestingWave extends Wave implements Updater {

    private static final int TOTAL_ROUND = 1;

    public TestingWave(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_ROUND);
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  (player.isAlive() &&
                gameObj.length() == 1 &&
                super.getRoundProgression() >= 1);
    }

    @Override
    protected void spawnEnemies(){
        ArrayList<Property> properties = getProperties(gameObj);
        int enemyNum =  1 + currentRound;
        for (int i = 0; i < enemyNum; i++){
            gameObj.add(new Skeleton(properties));
        }
    }

    @Override
    protected int getRoundDuration(){
        return 10;
    }
}
