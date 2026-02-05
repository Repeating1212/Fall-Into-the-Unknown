package Level.Controllers;

import Data.Interface.OverlayController;
import Data.Supplier.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class LoseController extends OverlayController {

    @FXML
    private Pane rootPane;
    @FXML private Text coinLabel;

    @FXML
    public void initialize() {}

    @FXML
    private void handleExitButton(ActionEvent event) {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    @FXML
    private void handlePlayAgain() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.LEVEL_01, fileManager);
    }
}