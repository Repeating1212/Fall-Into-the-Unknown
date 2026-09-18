package Level.Lvl_1.Waves;

import Level.BaseLevel.Manager.Updater;
import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Enemy.Object.Rat;
import Level.Lvl_1.Enemy.Object.SlimeGrey;
import Level.Lvl_1.Enemy.Object.SlimeWhite;

import java.util.ArrayList;
import java.util.Random;

public class Wave2 extends Wave implements Updater {

    private final static int TOTAL_ROUND = 3;


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
        ArrayList<Property> properties = getProperties(gameObj);

        Random random = new Random();
        int totalNum = 5 + currentRound;
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

    @Override
    protected int getRoundDuration(){
        return Math.max(8, gameObj.length());
    }
}
