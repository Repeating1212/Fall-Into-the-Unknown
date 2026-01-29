package Game_UI.Controller;

import Game_UI.Controller.Icons_Scene.CharacterScene;
import Game_UI.Controller.Icons_Scene.EncyclopediaScene;
import Game_UI.Controller.Icons_Scene.SkillScene;
import Game_UI.Controller.Icons_Scene.StoreScene;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Setting/Setting.fxml"));
            Pane settingWindow = loader.load();
            rootPane.getChildren().add(settingWindow);
            Setting settingController = loader.getController();
            settingController.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCharacterState() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Icons_Scene/CharacterScene/UnknownScene.fxml"));
            Pane pane = loader.load();
            rootPane.getChildren().add(pane);
            CharacterScene characterScene = loader.getController();
            characterScene.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showSkillScene() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Icons_Scene/SkillScene/UnknownScene.fxml"));
            Pane pane = loader.load();
            rootPane.getChildren().add(pane);
            SkillScene skillScene = loader.getController();
            skillScene.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showEncyclopedia() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Icons_Scene/EncyclopediaScene/UnknownScene.fxml"));
            Pane pane = loader.load();
            rootPane.getChildren().add(pane);
            EncyclopediaScene encyclopediaScene = loader.getController();
            encyclopediaScene.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStore() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Icons_Scene/StoreScene/UnknownScene.fxml"));
            Pane pane = loader.load();
            rootPane.getChildren().add(pane);
            StoreScene storeScene = loader.getController();
            storeScene.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
