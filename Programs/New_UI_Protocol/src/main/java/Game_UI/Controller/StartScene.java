package Game_UI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class StartScene {

    @FXML
    public void test(ActionEvent event) {
        System.out.println("Test");
        try {
            // Get the button that was clicked
            Button clickedButton = (Button) event.getSource();

            // Load the second scene
            Parent secondScene = FXMLLoader.load(getClass().getResource("/Game_UI/GameScene/GameScene.fxml"));

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
//
//    @FXML
//    private void switchToLevelScene(ActionEvent event) {
//        try {
//            // Get the button that was clicked
//            Button clickedButton = (Button) event.getSource();
//
//            // Load the second scene
//            Parent secondScene = FXMLLoader.load(
//                    getClass().getResource("LevelScene.fxml")
//            );
//
//            // Get stage from ANY node in the current scene
//            Stage stage = (Stage) clickedButton.getScene().getWindow();
//
//            // Set new scene
//            Scene scene = new Scene(secondScene);
//            stage.setScene(scene);
//            stage.show();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
}
