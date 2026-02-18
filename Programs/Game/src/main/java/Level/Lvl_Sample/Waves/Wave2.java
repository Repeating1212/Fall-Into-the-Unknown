package Level.Lvl_Sample.Waves;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Rat;
import Level.Lvl_Sample.Enemy.Object.SlimeGrey;
import Level.Lvl_Sample.Enemy.Object.SlimeWhite;

import java.util.ArrayList;
import java.util.Random;

public class Wave2 extends Wave implements Updater{

    private final static int TOTAL_WAVE = 3;


    public Wave2(SceneView sceneView, Player player){
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

        Random random = new Random();
        int totalNum = 5 + currentWave;
        int slimeNum = random.nextInt(totalNum + 1);
        int whiteSlimeNum = (slimeNum > 0) ? random.nextInt(slimeNum + 1) : 0;
        int greySlimeNum = slimeNum - whiteSlimeNum;
        int ratNum = totalNum - slimeNum;

        for (int i = 0; i < whiteSlimeNum; i++){
            gameObj.add(new SlimeWhite(properties));
        }
        for (int i = 0; i < greySlimeNum; i++){
            gameObj.add(new SlimeGrey(getProperties(gameObj)));
        }

        for (int i = 0; i < ratNum; i++){
            gameObj.add(new Rat(getProperties(gameObj)));
        }
    }
}
