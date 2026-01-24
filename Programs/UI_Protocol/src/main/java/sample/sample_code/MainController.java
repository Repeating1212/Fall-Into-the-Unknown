package sample.sample_code;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainController {

    @FXML private Button upgradeBtn, skillStoreBtn, bossNotesBtn, settingsBtn;

    @FXML
    private void switchToLevelScene(ActionEvent event) {
        try {
            // Get the button that was clicked
            Button clickedButton = (Button) event.getSource();

            // Load the second scene
            Parent secondScene = FXMLLoader.load(
                    getClass().getResource("LevelScene.fxml")
            );

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
    private void initialize() {

        int transitionLength = -75;

        upgradeBtn.setOnMouseEntered(e -> {
            animateButton(upgradeBtn, transitionLength);
        });
        upgradeBtn.setOnMouseExited(e -> {
            animateButton(upgradeBtn, 0);
        });

        skillStoreBtn.setOnMouseEntered(e -> {
            animateButton(skillStoreBtn, transitionLength);
        });
        skillStoreBtn.setOnMouseExited(e -> {
            animateButton(skillStoreBtn, 0);
        });

        bossNotesBtn.setOnMouseEntered(e -> {
            animateButton(bossNotesBtn, transitionLength);
        });
        bossNotesBtn.setOnMouseExited(e -> {
            animateButton(bossNotesBtn, 0);
        });

        settingsBtn.setOnMouseEntered(e -> {
            animateButton(settingsBtn, transitionLength);
        });
        settingsBtn.setOnMouseExited(e -> {
            animateButton(settingsBtn, 0);
        });
    }

    @FXML
    private void gameDesignerBtnClicked(ActionEvent event) {
        System.out.println("Game Designer Button Clicked!!");
    }
    @FXML
    private void aboutUsBtnClicked(ActionEvent event) {
        System.out.println("About Us Button Clicked!!");
    }
    @FXML
    private void level1BtnClicked(ActionEvent event) {
        System.out.println("Level 1 Button Clicked!!");
    }
    @FXML
    private void level2BtnClicked(ActionEvent event) {
        System.out.println("Level 2 Button Clicked!!");
    }
    @FXML
    private void level3BtnClicked(ActionEvent event) {
        System.out.println("Level 3 Button Clicked!!");
    }
    @FXML
    private void level4BtnClicked(ActionEvent event) {
        System.out.println("Level 4 Button Clicked!!");
    }
    @FXML
    private void level5BtnClicked(ActionEvent event) {
        System.out.println("Level 5 Button Clicked!!");
    }
    @FXML
    private void level6BtnClicked(ActionEvent event) {
        System.out.println("Level 6 Button Clicked!!");
    }

    @FXML
    private void printTest(ActionEvent event){
        System.out.println("Testing");
    }

    // Helper Method

    private void animateButton(Button btn, double targetX) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToX(targetX);
        tt.play();
    }
}
