package Game_UI.GameScene;

import Data.Interface.OverlayController;
import Data.Supplier.SceneLoader;
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
