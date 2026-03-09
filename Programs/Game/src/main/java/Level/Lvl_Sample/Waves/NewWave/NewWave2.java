package Level.Lvl_Sample.Waves.NewWave;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.SlimeWhite;
import Level.Lvl_Sample.Waves.Updater;
import Level.Lvl_Sample.Waves.Wave;

import java.util.ArrayList;

public class NewWave2 extends Wave implements Updater {

    private final static int TOTAL_ROUND = 2;


    public NewWave2(SceneView sceneView, Player player){
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
        int whiteSlimeNum = currentRound + 2;
        for (int i = 0; i < whiteSlimeNum; i++){
            gameObj.add(new SlimeWhite(properties));
        }
    }

    @Override
    protected int getRoundDuration(){
        return 10;
    }
}
