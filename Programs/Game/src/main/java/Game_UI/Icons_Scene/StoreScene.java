package Game_UI.Icons_Scene;

import Game_Data.DataManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StoreScene {
    private Pane rootPane;
    @FXML private Button returnButton;

    @FXML
    public void initialize() {
        returnButton.setOnMouseEntered(e -> {
            returnButtonAnimation(returnButton, 30);
        });
        returnButton.setOnMouseExited(e -> {
            returnButtonAnimation(returnButton, 0);
        });
    }

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }


    @FXML
    private void handleReturn() {
        try {
            // Reload the FXML
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/Game_UI/GameScenes/GameScene/GameScene.fxml"));
            Scene startScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(startScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private Method

    private void returnButtonAnimation(Button btn, double targetY) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToY(targetY);
        tt.play();
    }
}
