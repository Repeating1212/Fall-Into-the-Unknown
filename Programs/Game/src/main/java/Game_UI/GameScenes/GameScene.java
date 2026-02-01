package Game_UI.GameScenes;

import Game_UI.Icons_Scene.CharacterScene;
import Game_UI.Icons_Scene.EncyclopediaScene;
import Game_UI.Skill_Scene.SkillConfig;
import Game_UI.Skill_Scene.SkillScene;
import Game_UI.Icons_Scene.StoreScene;
import Game_Data.DataManager;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameScene {
    @FXML private ImageView Forest, Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal;
    @FXML private ImageView settingIcon;
    @FXML private Pane rootPane;
    @FXML private Text CoinLabel;
    @FXML private ImageView skill01, skill02, skill03, skill04;

    private RotateTransition settingRotationEnter;
    private RotateTransition settingRotationExit;

    private ImageView[] levels;
    private ImageView[] skillIcons;

    @FXML
    public void initialize() {
        levels = new  ImageView[]{Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal};
        skillIcons  = new ImageView[]{skill01, skill02, skill03, skill04};

        DataManager.loadData();
        for (ImageView level : levels) level.setOpacity(0.5);
        setupSettingIconAnimation();
        CoinLabel.setText(String.valueOf(DataManager.getGameData().getCoins()));
        updateSkillIcons();
    }

    private void setupSettingIconAnimation() {
        // Create the rotation animation
        settingRotationEnter = new RotateTransition(Duration.seconds(0.5), settingIcon);
        settingRotationEnter.setFromAngle(0);
        settingRotationEnter.setToAngle(90);
        settingRotationEnter.setCycleCount(1);

        settingRotationExit = new RotateTransition(Duration.seconds(0.5), settingIcon);
        settingRotationExit.setFromAngle(0);
        settingRotationExit.setToAngle(-90);
        settingRotationExit.setCycleCount(1);
    }

    @FXML
    private void handleSettingMouseEntered(MouseEvent event) {
        settingRotationEnter.playFromStart();
    }
    @FXML
    private void handleSettingMouseExit(MouseEvent event) {
        settingRotationExit.playFromStart();
    }

    @FXML
    private void showSetting() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/GameScenes/Setting/Setting.fxml"));
            Pane settingWindow = loader.load();
            rootPane.getChildren().add(settingWindow);
            Setting settingController = loader.getController();
            settingController.setRootPane(rootPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void showCharacterState() {
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

    @FXML
    private void showSkillScene_Skill01() {
        loadSkillScene(0);
    }

    @FXML
    private void showSkillScene_Skill02() {
        loadSkillScene(1);
    }

    @FXML
    private void showSkillScene_Skill03() {
        loadSkillScene(2);
    }

    @FXML
    private void showSkillScene_Skill04() {
        loadSkillScene(3);
    }

    @FXML
    private void showEncyclopedia() {
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

    @FXML
    private void showStore() {
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

    @FXML
    private void loadLevel01(MouseEvent event) {  // Change to MouseEvent
        try {
            ImageView clickedImageView = (ImageView) event.getSource();
            Parent secondScene = FXMLLoader.load(getClass().getResource("/Level/LevelScene.fxml"));
            Stage stage = (Stage) clickedImageView.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Private Method

    private void loadSkillScene(int selectSkill){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillScene.fxml"));
            Pane pane = loader.load();
            SkillScene skillScene = loader.getController();
            skillScene.initializeData(rootPane);
            rootPane.getChildren().add(pane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updateSkillIcons(){
        Image[] skillImages = SkillConfig.SKILL_ICONS;
        int[] equipedSkill = DataManager.getSkillData().getEquipedSkill();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < equipedSkill.length; j ++){
                if(equipedSkill[i] == j){
                    skillIcons[i].setImage(skillImages[j]);
                }
            }
        }
    }
}
