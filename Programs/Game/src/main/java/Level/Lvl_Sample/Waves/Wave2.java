package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;
import Level.Lvl_Sample.Enemy.Object.SlimeWhite;

import java.util.ArrayList;
import java.util.Random;

public class Wave2 extends Wave implements Updater{

    private final static int TOTAL_WAVE = 1;


    public Wave2(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
    }

    // Override Method

    @Override
    public boolean isComplete(){
        return  player.isAlive() &&
                gameObj.length() == 1 &&
                super.getProgress() >= 1;
    }

    @Override
    protected void spawnEnemies(){
        Random random = new Random();
        int num =  random.nextInt(2,5);
        for (int i = 0; i < num; i++){
            SlimeWhite slimeWhite = new SlimeWhite(getProperties(gameObj));
            gameObj.add(slimeWhite);
        }
        for (int i = 0; i < 5 - num; i++){
            SlimeGrey slimeGrey = new SlimeGrey(getProperties(gameObj));
            gameObj.add(slimeGrey);
        }
    }
}
