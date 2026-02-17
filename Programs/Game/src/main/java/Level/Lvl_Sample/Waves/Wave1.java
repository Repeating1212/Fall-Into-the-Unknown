package Level.Lvl_Sample.Waves;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;

import java.util.ArrayList;

public class Wave1  extends Wave implements Updater{

    private static final int TOTAL_WAVE = 5;


    public Wave1(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  (player.isAlive() &&
                (waveTimer.isEnd() || gameObj.length() == 1) &&
                super.getRoundProgress() >= 1);
    }

    @Override
    protected void spawnEnemies(){
        ArrayList<Property> properties = getProperties(gameObj);

        int ratNum =  2 + currentWave;
        for (int i = 0; i < ratNum; i++){
            gameObj.add(new Rat(properties));
        }
        for (int i = 0; i < (currentWave - ratNum); i++){
            gameObj.add(new SlimeGrey(properties));
        }
    }
}
