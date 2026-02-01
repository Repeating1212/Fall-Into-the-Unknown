package Game_UI.GameScenes;

import Game_UI.OverlayController;
import Game_UI.SceneLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting implements OverlayController {
    @FXML
    private Pane SettingPane;
    private Pane rootPane;
    @FXML
    public void initialize() {}

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }

    @FXML
    private void handleQuit() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.START);
    }

    @FXML
    private void handleClose() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(SettingPane);
    }
}
