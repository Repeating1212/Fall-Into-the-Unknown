package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SceneView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LevelManager implements Updater{

    protected final Player player;
    protected final SceneView sceneView;
    protected final ArrayData<GameObject> gameObj = new ArrayData();
    protected boolean gamePause = false;

    private int currentWave = 0;

    private Wave[] waves;

    public LevelManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        gameObj.add(player);

        waves = new Wave[]{
                new Wave1(sceneView, player),
                new Wave1(sceneView, player)
        };

        System.out.println(waves.length);
    }

    public void updateObjects(double deltaTime){
        if (gamePause) return;

        waves[currentWave].updateObjects(deltaTime);
        if(waves[currentWave].isComplete()) currentWave ++;

        gamePause = handleWinLose();
    }

    // Private Method

    private boolean handleWinLose(){
        if (currentWave >= waves.length){
            sceneView.showWinScreen(5);
            return true;
        }
        if (waves[currentWave].isLose()){
            sceneView.showLoseScreen();
            return true;
        }

        return false;
    }

}
