package Level.Lvl_Sample.Waves;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;

import java.util.ArrayList;

public class Wave1  extends Wave implements Updater{

    private static final int TOTAL_ROUND = 5;


    public Wave1(SceneView sceneView, Player player){
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
        return Math.max(8, gameObj.length());
    }
}
