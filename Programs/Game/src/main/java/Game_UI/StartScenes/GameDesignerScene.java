package Game_UI.StartScenes;

import Game_Data.Supplier.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class GameDesignerScene {

    @FXML private Pane rootPane;

    @FXML
    public void initialize() {}

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.START);
    }
}
