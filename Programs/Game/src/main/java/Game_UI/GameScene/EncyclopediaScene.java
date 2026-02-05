package Game_UI.GameScene;

import Game_Data.Interface.SceneInterface;
import Game_Data.Supplier.SceneLoader;
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
