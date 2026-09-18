package Level.Lvl_1.Waves.NewWave;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Enemy.Object.Rat;
import Level.BaseLevel.Manager.Updater;
import Level.BaseLevel.Manager.Wave;

import java.util.ArrayList;

public class NewWave1 extends Wave implements Updater {

    private static final int TOTAL_ROUND = 3;

    public NewWave1(SceneView sceneView, Player player){
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
        ArrayList<Property> properties = getProperties(gameObj);
        int ratNum =  2 + currentRound;
        for (int i = 0; i < ratNum; i++){
            gameObj.add(new Rat(properties));
        }
    }

    @Override
    protected int getRoundDuration(){
        return 10;
    }
}
