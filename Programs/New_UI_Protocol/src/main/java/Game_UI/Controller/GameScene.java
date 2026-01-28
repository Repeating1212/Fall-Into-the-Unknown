package Game_UI.Controller;

import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class GameScene {
    @FXML private ImageView Forest, Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal;
    @FXML private ImageView settingIcon;
    @FXML private Pane rootPane;

    private RotateTransition settingRotationEnter;
    private RotateTransition settingRotationExit;

    private ImageView[] levels;

    @FXML
    public void initialize() {
        levels = new  ImageView[]{Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal};
        for (ImageView level : levels){
            level.setOpacity(0.5);
        }
        setupSettingIconAnimation();
    }

    private void setupSettingIconAnimation() {
        // Create the rotation animation
        settingRotationEnter = new RotateTransition(Duration.seconds(0.5), settingIcon);
        settingRotationEnter.setFromAngle(0);    // Start from 0 degrees
        settingRotationEnter.setToAngle(90);    // Rotate to 360 degrees
        settingRotationEnter.setCycleCount(1);   // Play once

        settingRotationExit = new RotateTransition(Duration.seconds(0.5), settingIcon);
        settingRotationExit.setFromAngle(0);    // Start from 0 degrees
        settingRotationExit.setToAngle(-90);    // Rotate to 360 degrees
        settingRotationExit.setCycleCount(1);   // Play once
    }

    @FXML
    private void handleSettingMouseEntered(MouseEvent event) {
        settingRotationEnter.playFromStart();
    }
    @FXML
    private void handleSettingMouseExit(MouseEvent event) {
        settingRotationExit.playFromStart();
    }

    public void showSetting() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Setting.fxml"));
            Pane settingWindow = loader.load();
            rootPane.getChildren().add(settingWindow);
            Setting settingController = loader.getController();
            settingController.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
