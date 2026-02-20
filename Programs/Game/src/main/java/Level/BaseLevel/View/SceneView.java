package Level.BaseLevel.View;

import Data.Loader.SceneLoader;
import Game_File.FileManager;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import Level.BaseLevel.Controllers.WinController;
import Level.BaseLevel.Manager.GameTicks;
import Level.BaseLevel.Controllers.PauseController;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;


import java.util.ArrayList;

public class SceneView {

    private final Pane rootPane;
    private final Pane[] spriteLayer;

    private ArrayList<DisplayableObject> objectList = new ArrayList<>();
    private final FileManager fileManager;

    private final BossHealthBar bossHealthView;
    private final PlayerHeartView playerHeartView;
    private final SkillBoxView skillBoxView;
    private final MapView mapView;

    public SceneView(Pane rootPane, Pane[] spriteLayer, ImageView[] hearts,
                     ProgressBar progressBar, ImageView map,
                     ImageView[] skills, StackPane[] skillBackgrounds, Rectangle[] skillCooldowns,
                     FileManager fileManager){
        this.rootPane = rootPane;
        this.spriteLayer = spriteLayer;
        this.fileManager = fileManager;

        bossHealthView = new BossHealthBar(progressBar);
        playerHeartView = new PlayerHeartView(hearts);
        skillBoxView = new SkillBoxView(skills, skillBackgrounds, skillCooldowns, fileManager);
        mapView = new MapView(map);
    }

    public void updateObjects(ArrayList<DisplayableObject> toDisplay) {
        if (toDisplay == null) return;

        // Remove node that shouldn't be there anymore
        for (DisplayableObject object : objectList){
            if (! toDisplay.contains(object)) {
                Pane layer = spriteLayer[object.getLayer()];
                layer.getChildren().removeAll(object.getSprite());
            }
        }

        // Add new sprites that aren't already present
        for (DisplayableObject object : toDisplay){
            if(! objectList.contains(object)){
                Pane layer = spriteLayer[object.getLayer()];
                layer.getChildren().addAll(object.getSprite());
            }
        }

        objectList = new ArrayList<DisplayableObject>();
        objectList.addAll(toDisplay);
    }

    public void addDebugHitBox(ArrayList<Rectangle> debugHitBox){
        for (Rectangle rectangle : debugHitBox){
            Pane layer = spriteLayer[spriteLayer.length - 1];
            if (! layer.getChildren().contains(rectangle)) {
                layer.getChildren().add(rectangle);
            }
        }
    }

    public void showWinScreen(int coinValue) {
        WinController winController = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.WIN, fileManager);
        assert winController != null;
        winController.updateCoinLabel(coinValue);
    }

    public void showLoseScreen() {
        SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.LOSE, fileManager);
    }

    public void showPauseScreen(GameTicks gameTicks) {
        PauseController pauseController = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.PAUSE, fileManager);
        assert pauseController != null;
        pauseController.setGameTicks(gameTicks);
    }

    public SkillBoxView getSkillBoxView(){
        return skillBoxView;
    }



    // Middle Man

    public void updateBossHealthBar(double progress){
        bossHealthView.updateHealthBar(progress);
    }

    public void updatePlayerHeartView(int heart){
        playerHeartView.updatePlayerHeartView(heart);
    }

    public void updateSkillCooldowns(double[] cooldownPercentages){
        skillBoxView.updateSkillCooldowns(cooldownPercentages);
    }
}
