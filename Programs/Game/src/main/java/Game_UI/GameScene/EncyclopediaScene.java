package Game_UI.GameScene;

import Data.Interface.SceneInterface;
import Data.Loader.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class EncyclopediaScene extends SceneInterface {
    @FXML private Pane rootPane;

    @FXML
    public void initialize() {}

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }
}
