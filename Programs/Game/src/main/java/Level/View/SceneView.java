package Level.View;

import Game_Data.Interface.SceneInterface;
import Game_Data.Supplier.SceneLoader;
import LoadFile.FileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Level.Controllers.WinController;
import Level.Controllers.GameTicks;
import Level.Controllers.PauseController;
import Level.Objects.Base_Class.DisplayableObject;


import java.io.IOException;
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

    public void updateObjectsInSceneNew(ArrayList<DisplayableObject> toDisplay) {
        if (toDisplay == null) return;

        // Remove node that shouldn't be there anymore
        for (DisplayableObject object : objectList){
            if (! toDisplay.contains(object)) {
                rootPane.getChildren().remove(object.getSprite());
            }
        }

        // Add new sprites that aren't already present
        for (DisplayableObject object : toDisplay){
            if(! objectList.contains(object)){
                rootPane.getChildren().add(object.getSprite());
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

    public void setMoneyValue(int money){
        coinLabel.setText(String.valueOf(money));
    }

    public SkillBoxView getSkillBoxView(){
        return skillBoxView;
    }

    public void showWinScreen(int coinValue) {
        WinController winController = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.WIN, fileManager);
        assert winController != null;
        winController.updateCoinLabel(coinValue);
//            winScreen.setPrefHeight(500);
//            winScreen.setPrefWidth(800);
//            winScreen.setLayoutX(200);
//            winScreen.setLayoutY(85);
    }

    public void showLoseScreen() {
        SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.LOSE, fileManager);
    }

    public void showPauseScreen(GameTicks gameTicks) {
        PauseController pauseController = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.PAUSE, fileManager);
        assert pauseController != null;
        pauseController.setGameTicks(gameTicks);
    }

    public void updateSkillCooldowns(int skillID , double cooldownPercentages){
        skillBoxView.updateSkillCooldowns(skillID ,cooldownPercentages);
    }
    // Middle Man

    public void updateBossHealthBar(double progress){
        bossHealthView.updateHealthBar(progress);
    }

    public void updatePlayerHeartView(int heart){
        playerHeartView.updatePlayerHeartView(heart);
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
