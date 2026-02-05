package Game_UI.GameScenes;

import Game_Data.Interface.OverlayController;
import Game_Data.Supplier.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Setting extends OverlayController {
    @FXML
    private Pane SettingPane;

    @FXML
    public void initialize() {}

    @FXML
    private void handleQuit() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.START, fileManager);
    }

    @FXML
    private void handleClose() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(SettingPane);
    }
}
