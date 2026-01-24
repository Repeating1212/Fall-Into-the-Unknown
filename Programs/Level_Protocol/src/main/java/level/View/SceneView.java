package level.View;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import level.Controllers.WinController;
import level.Controllers.GameTicks;
import level.Controllers.PauseController;
import level.Objects.Base_Class.DisplayableObject;


import java.io.IOException;
import java.util.ArrayList;

public class SceneView {

    private final Pane rootPane;
    private final Text coinLabel;
    private ArrayList<DisplayableObject> objectList = new ArrayList<>();
    private final BossHealthBar bossHealthView;
    private final PlayerHeartView playerHeartView;
    private final SkillBoxView skillBoxView;
    private final MapView mapView;

    public SceneView(Pane rootPane, Text moneyLabel, ImageView[] hearts, ImageView[] skills, StackPane[] skillBackgrounds,
                     ProgressBar progressBar, ImageView map, Rectangle[] skillCooldowns){
        this.rootPane = rootPane;
        this.coinLabel = moneyLabel;
        bossHealthView = new BossHealthBar(progressBar);
        playerHeartView = new PlayerHeartView(hearts);
        skillBoxView = new SkillBoxView(skills, skillBackgrounds, skillCooldowns);
        mapView = new MapView(map);
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
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("win-screen.fxml"));
            Pane winScreen = loader.load();
            winScreen.setPrefHeight(500);
            winScreen.setPrefWidth(800);
            winScreen.setLayoutX(200);
            winScreen.setLayoutY(85);
            rootPane.getChildren().add(winScreen);
            WinController endGameController = loader.getController();
            endGameController.updateCoinLabel(coinValue);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showLoseScreen() {
        try {
            Pane winScreen = FXMLLoader.load(getClass().getResource("lose-screen.fxml"));
            winScreen.setPrefHeight(500);
            winScreen.setPrefWidth(800);
            winScreen.setLayoutX(200);
            winScreen.setLayoutY(85);
            rootPane.getChildren().add(winScreen);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showPauseScreen(GameTicks gameTicks) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pause-screen.fxml"));
            Pane pauseScreen = loader.load();
            pauseScreen.setPrefHeight(500);
            pauseScreen.setPrefWidth(800);
            pauseScreen.setLayoutX(200);
            pauseScreen.setLayoutY(85);
            rootPane.getChildren().add(pauseScreen);
            PauseController pauseGameController = loader.getController();
            pauseGameController.setData(rootPane, gameTicks);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSkillCooldowns(double[] cooldownPercentages){
        skillBoxView.updateSkillCooldowns(cooldownPercentages);
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
