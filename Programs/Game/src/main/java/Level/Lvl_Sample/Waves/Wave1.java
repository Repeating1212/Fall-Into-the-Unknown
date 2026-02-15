package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;

import java.util.ArrayList;
import java.util.Random;

public class Wave1  extends Wave implements Updater{

    private static final int TOTAL_WAVE = 2;


    public Wave1(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  (player.isAlive() &&
                waveTimer.isEnd() &&
                super.getProgress() >= 1) ||
                gameObj.length() == 1;
    }

    @Override
    protected void spawnEnemies(){
        Random random = new Random();
        int num =  random.nextInt(2,5);
        for (int i = 0; i < num; i++){
            ArrayList<Property> properties = getProperties(gameObj);
            gameObj.add(new Rat(properties));
        }
    }
}
