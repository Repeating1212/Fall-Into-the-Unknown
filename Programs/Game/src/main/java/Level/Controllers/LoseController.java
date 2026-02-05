package Level.Controllers;

import Game_Data.Interface.OverlayController;
import Game_Data.Interface.SceneInterface;
import Game_Data.Supplier.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

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