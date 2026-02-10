package Level.Lvl_Sample.Managers;

import Level.BaseLevel.Manager.LevelData;
import Level.BaseLevel.Manager.Updater;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import javafx.scene.shape.Rectangle;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.View.SceneView;

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
        // Empty
    }

    // Private Method

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
