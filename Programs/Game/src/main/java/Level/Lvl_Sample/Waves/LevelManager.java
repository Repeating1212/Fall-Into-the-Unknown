package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;

public class LevelManager implements Updater{

    protected final Player player;
    protected final SceneView sceneView;
    protected final ArrayData<GameObject> gameObj = new ArrayData();
    protected boolean gameEnd = false;

    private int currentWave = 0;

    private Wave[] waves;

    public LevelManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        gameObj.add(player);

        waves = new Wave[]{
                new Wave1(sceneView, player),
                new Wave2(sceneView, player)
        };
    }

    public void updateObjects(double deltaTime){
        if (gameEnd) return;

        waves[currentWave].updateObjects(deltaTime);
        gameEnd = handleWinLose();

        if (gameEnd) return;
        handleNextWave();
    }

    // Private Method

    private boolean handleWinLose(){
        if (currentWave == waves.length -1 && waves[currentWave].isComplete()){
            sceneView.showWinScreen(5);
            return true;
        }
        if (waves[currentWave].isLose()){
            sceneView.showLoseScreen();
            return true;
        }

        return false;
    }

    private void handleNextWave(){
        if (waves[currentWave].isComplete()){
            currentWave ++;
            waves[currentWave].setGameObj( waves[currentWave -1].getGameObj() );
        }
    }

}
