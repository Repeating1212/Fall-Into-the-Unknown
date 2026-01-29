package Game_UI.StartScenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScene {

    @FXML
    public void loadGameScene(ActionEvent event) {
        try {
            // Get the button that was clicked
            Button clickedButton = (Button) event.getSource();

            // Load the second scene
            Parent secondScene = FXMLLoader.load(getClass().getResource("/Game_UI/GameScenes/GameScene/GameScene.fxml"));

            // Get stage from ANY node in the current scene
            Stage stage = (Stage) clickedButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadGameDesigner(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();

            Parent secondScene = FXMLLoader.load(getClass().getResource("/Game_UI/StartScenes/GameDesignerScene/UnknownScene.fxml"));
            Stage stage = (Stage) clickedButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quitGame(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
