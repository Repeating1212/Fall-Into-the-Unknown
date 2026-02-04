package Game_UI.GameScenes;

import Game_Data.OverlayController;
import Game_Data.Supplier.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

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
