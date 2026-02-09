package Level.Controllers;

import Data.Interface.OverlayController;
import Data.Loader.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class PauseController extends OverlayController {
    @FXML
    private Pane PauseScreen;
    private GameTicks gameTicks;

    @FXML
    public void initialize() {}

    public void setGameTicks(GameTicks gameTicks){
        this.gameTicks = gameTicks;
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    @FXML
    private void handlePlayAgain() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.LEVEL_01, fileManager);
    }

    @FXML
    private void handleResumeButton() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(PauseScreen);
        gameTicks.handlePause();
    }
}
