package Lvl_Sample.Managers;

import BaseLevel.Manager.LevelData;
import BaseLevel.Manager.Updater;
import BaseLevel.Objects.Class_Concrete.Player;
import javafx.scene.shape.Rectangle;
import BaseLevel.Objects.Class_Base.GameObject;
import BaseLevel.View.SceneView;

import java.util.ArrayList;

public class MainManager extends Updater {

    public MainManager(SceneView sceneView, Player player, LevelData levelData, Observer observer){
        super(sceneView, player, levelData, observer, new LvlSupplier_Sample());

        observer.setPlayer(player);
        observer.setEnemy(player);
    }

    // Override Method

    @Override
    protected void handleWinCondition(){
        closeBoss();
        spawnPortal();
    }

    // Private Method

    private void closeBoss(){
        boss.removeDisplay();
        levelData.removeObjects(boss);

    }

    private void spawnPortal(){
        portal.setPosition(boss.getX(), boss.getY());
        levelData.addObjects(portal);
    }

    private void addHitBoxDebug(){
        // For Debug purpose
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (GameObject object : levelData.getGameObjects()) {
            rectangles.add(object.getProperty().getDebugHitBox());
            object.getProperty().showDebugHitBox();
        }
        sceneView.addDebugHitBox(rectangles);
    }
}
