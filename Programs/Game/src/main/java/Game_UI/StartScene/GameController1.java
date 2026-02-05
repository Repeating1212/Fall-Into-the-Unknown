package Game_UI.StartScene;

import Game_Data.Interface.SceneInterface;
import Game_Data.Supplier.ImageLoader;
import Game_Data.Supplier.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class GameController1 extends SceneInterface {

    @FXML private Pane rootPane;
    @FXML private ImageView fallingGirl;

    @FXML
    private void initialize(){
        fallingGirl.setImage(ImageLoader.STARTSCENE_BACKGROUND);
    }

    @FXML
    private void loadGameScene() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    @FXML
    private void loadGameDesigner() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME_DESIGNER, fileManager);
    }

    @FXML
    public void quitGame(ActionEvent event) {
        fileManager.saveFile();

        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
