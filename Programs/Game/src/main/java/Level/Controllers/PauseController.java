package Level.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class PauseController {
    @FXML
    private Pane PauseScreen;
    private Pane rootPane;
    private GameTicks gameTicks;

    @FXML
    public void initialize() {}

    public void setData(Pane rootPane, GameTicks gameTicks){
        this.rootPane = rootPane;
        this.gameTicks = gameTicks;
    }

    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePlayAgain() {
        try {
            // Reload the FXML
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/level/LevelScene.fxml"));
            Scene gameScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleResumeButton() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(PauseScreen);
        gameTicks.handlePause();
    }
}
