package Level.BaseLevel.Manager;

import Data.DataClass.ArrayData;
import Data.DataClass.LevelType;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Waves.NewWave.NewWave1;
import Level.Lvl_1.Waves.NewWave.NewWave2;
import Level.Lvl_1.Waves.NewWave.NewWave3;
import Level.Lvl_1.Waves.NewWave.NewWave4;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class LevelManager implements Updater {

    protected final Player player;
    protected final SceneView sceneView;
    protected final ArrayData<GameObject> gameObj = new ArrayData();
    protected boolean gameEnd = false;

    private int currentWave = 0;

    private final Wave[] waves;

    public LevelManager(SceneView sceneView, Player player, LevelType levelType){
        this.player = player;
        this.sceneView = sceneView;
        gameObj.add(player);

        waves = levelType.getWaves(sceneView, player);
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

    // Debug usage

    private void addHitBoxDebug(){
        // For Debug purpose
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (GameObject object : gameObj.get()) {
            rectangles.add(object.getProperty().getDebugHitBox());
            object.getProperty().showDebugHitBox();
        }
        sceneView.addDebugHitBox(rectangles);
    }

}
