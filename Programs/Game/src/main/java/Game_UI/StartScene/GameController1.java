package Game_UI.StartScene;

import Data.Interface.SceneInterface;
import Data.Loader.ImageLoader;
import Data.Loader.SceneLoader;
import Game_UI.GameScene.GameController2;
import LoadFile.FileManager;
import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameController1 extends SceneInterface {

    @FXML private Pane rootPane, imagePane;
    @FXML private ImageView fallingGirl;
    @FXML private Button gameBtn, gameDesignerBtn, quitBtn;

    @FXML
    private void initialize(){
        fallingGirl.setImage(ImageLoader.STARTSCENE_BACKGROUND);
        initializeAnimation();
        createFloatingEffect(fallingGirl);
        createFloatingEffect(imagePane);
    }

    @FXML
    private void loadGameScene() {
        SceneLoader.switchGameSceneWithSmoke(rootPane, fileManager);
    }

    @FXML
    private void loadGameDesigner() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME_DESIGNER, fileManager);
    }

    @FXML
    public void quitGame(ActionEvent event) {
        fileManager.saveFile();

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void initializeAnimation() {
        // Animate gameBtn
        TranslateTransition gameAnimation = new TranslateTransition(Duration.millis(500), gameBtn);
        gameBtn.setLayoutX(1200);
        gameAnimation.setToX(-325);
        gameAnimation.play();

        // Animate gameDesignerBtn with delay
        TranslateTransition designerAnimation = new TranslateTransition(Duration.millis(500), gameDesignerBtn);
        gameDesignerBtn.setLayoutX(1200);
        designerAnimation.setToX(-325);
        designerAnimation.setDelay(Duration.millis(100)); // Staggered start
        designerAnimation.play();

        // Animate quitBtn with more delay
        TranslateTransition quitAnimation = new TranslateTransition(Duration.millis(500), quitBtn);
        quitBtn.setLayoutX(1200);
        quitAnimation.setToX(-325);
        quitAnimation.setDelay(Duration.millis(200));
        quitAnimation.play();
    }

    public void createFloatingEffect(Node node) {
        // Floating animation
        Timeline floatTimeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(node.translateYProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1),
                        new KeyValue(node.translateYProperty(), -16, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.seconds(2),
                        new KeyValue(node.translateYProperty(), 0, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(node.translateYProperty(), -8, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.seconds(4),
                        new KeyValue(node.translateYProperty(), 0, Interpolator.EASE_BOTH)
                )
        );
        floatTimeline.setCycleCount(Timeline.INDEFINITE);

        // Rotation animation (independent timing)
        Timeline rotationTimeline = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(node.rotateProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(1.5),
                        new KeyValue(node.rotateProperty(), 3, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.seconds(3),
                        new KeyValue(node.rotateProperty(), 0, Interpolator.EASE_BOTH)
                )
        );
        rotationTimeline.setCycleCount(Timeline.INDEFINITE);

        Timeline rotationTimeline2 = new Timeline(
                new KeyFrame(Duration.ZERO,
                        new KeyValue(node.rotateProperty(), 0)
                ),
                new KeyFrame(Duration.seconds(10),
                        new KeyValue(node.rotateProperty(), -6, Interpolator.EASE_BOTH)
                ),
                new KeyFrame(Duration.seconds(18),
                        new KeyValue(node.rotateProperty(), 0, Interpolator.EASE_BOTH)
                )
        );
        rotationTimeline.setCycleCount(Timeline.INDEFINITE);

        // Play both animations
        floatTimeline.play();
        rotationTimeline.play();
        rotationTimeline2.play();
    }
}
