package Level.BaseLevel.View;

import Data.Loader.SceneLoader;
import LoadFile.FileManager;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Level.BaseLevel.Controllers.WinController;
import Level.BaseLevel.Manager.GameTicks;
import Level.BaseLevel.Controllers.PauseController;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;


import java.util.ArrayList;

public class SceneView {

    private final Pane rootPane;
    private final Text coinLabel;

    private ArrayList<DisplayableObject> objectList = new ArrayList<>();
    private final FileManager fileManager;

    private final BossHealthBar bossHealthView;
    private final PlayerHeartView playerHeartView;
    private final SkillBoxView skillBoxView;
    private final MapView mapView;

    public SceneView(Pane rootPane, Text moneyLabel, ImageView[] hearts,
                     ProgressBar progressBar, ImageView map, SkillBoxView skillBoxView, FileManager fileManager){
        this.rootPane = rootPane;
        this.coinLabel = moneyLabel;
        bossHealthView = new BossHealthBar(progressBar);
        playerHeartView = new PlayerHeartView(hearts);
        this.skillBoxView = skillBoxView;
        mapView = new MapView(map);
        this.fileManager = fileManager;
    }

    public void updateObjects(ArrayList<DisplayableObject> toDisplay) {
        if (toDisplay == null) return;

        // Remove node that shouldn't be there anymore
        for (DisplayableObject object : objectList){
            if (! toDisplay.contains(object)) {
                rootPane.getChildren().removeAll(object.getSprite());
            }
        }

        // Add new sprites that aren't already present
        for (DisplayableObject object : toDisplay){
            if(! objectList.contains(object)){
                rootPane.getChildren().addAll(object.getSprite());
            }
        }

        objectList = new ArrayList<DisplayableObject>();
        objectList.addAll(toDisplay);
    }

    public void addDebugHitBox(ArrayList<Rectangle> debugHitBox){
        for (Rectangle rectangle : debugHitBox){
            if (! rootPane.getChildren().contains(rectangle)) {
                rootPane.getChildren().add(rectangle);
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

    public void addRectangle(){
        Rectangle rectangle = new Rectangle();
        rectangle.setHeight(200);
        rectangle.setWidth(10);
        rectangle.setX(0);
        rectangle.setY(200);
        rectangle.setRotate(-90);
        rectangle.setFill(Color.rgb(255, 0, 0, 0.3));
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(2);
        rootPane.getChildren().add(rectangle);
    }
}
