package Game_UI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameDesignerScene {

    @FXML
    public void initialize() {}

    @FXML
    private void handleReturn(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();

            Parent secondScene = FXMLLoader.load(getClass().getResource("/Game_UI/StartScene/StartScene.fxml"));
            Stage stage = (Stage) clickedButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
