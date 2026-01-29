package Game_UI.Controller.Icons_Scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CharacterScene {

    private Pane rootPane;
    @FXML
    public void initialize() {}

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }


    @FXML
    private void handleReturn() {
        try {
            // Reload the FXML
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/Game_UI/GameScene/GameScene.fxml"));
            Scene startScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(startScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
