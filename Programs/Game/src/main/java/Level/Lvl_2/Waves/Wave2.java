package Level.Lvl_2.Waves;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Enemy.Object.Rat;
import Level.BaseLevel.Manager.Updater;
import Level.BaseLevel.Manager.Wave;
import Level.Lvl_2.Enemy.Object.Bullet;

import java.util.ArrayList;

public class Wave2 extends Wave implements Updater {

    private static final int TOTAL_ROUND = 3;

    public Wave2(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_ROUND);
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  (player.isAlive() &&
                (roundTimer.isEnd() || gameObj.length() == 1) &&
                super.getRoundProgression() >= 1);
    }

    @Override
    protected void spawnEnemies(){
        int ratNum =  10 + currentRound;
        for (int i = 0; i < ratNum; i++){
            Bullet bullet = new Bullet(player.getCenterPos());
            gameObj.add(bullet);
        }
    }

    @Override
    protected int getRoundDuration(){
        return 10;
    }
}
