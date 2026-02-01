package Game_UI.Icons_Scene;

import Game_UI.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class EncyclopediaScene {
    @FXML private Pane rootPane;

    @FXML
    public void initialize() {}

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME);
    }
}
