package Game_UI.StartScenes;

import Game_Data.DataManager;
import Game_UI.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class StartScene {

    @FXML private Pane rootPane;

    @FXML
    private void loadGameScene() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME);
    }

    @FXML
    private void loadGameDesigner() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME_DESIGNER);
    }

    @FXML
    public void quitGame(ActionEvent event) {
        DataManager.saveData();

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
