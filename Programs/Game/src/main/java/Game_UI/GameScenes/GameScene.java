package Game_UI.GameScenes;

import Game_Data.Config.ImageConfig;
import Game_UI.SceneLoader;
import Game_Data.DataManager;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameScene {
    @FXML private ImageView Forest, Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal, gameBackground;
    @FXML private ImageView settingView, coinView;
    @FXML private ImageView characterView, encyclopediaView, storeView;
    @FXML private ImageView skill01, skill02, skill03, skill04;
    @FXML private Pane rootPane;
    @FXML private Text CoinLabel;


    private RotateTransition settingRotationEnter;
    private RotateTransition settingRotationExit;

    private ImageView[] levels;
    private ImageView[] skillIcons;

    @FXML
    public void initialize() {
        levels = new  ImageView[]{Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal};
        skillIcons  = new ImageView[]{skill01, skill02, skill03, skill04};

        DataManager.loadData();
        initializeImage();
        initializeSkillImage();

        for (ImageView level : levels) level.setOpacity(0.5);
        setupSettingIconAnimation();
        CoinLabel.setText(String.valueOf(DataManager.getGameData().getCoins()));

    }

    // FXML Method

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
        SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SETTING);
    }

    @FXML
    private void loadCharacterState() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.CHARACTER);
    }

    @FXML
    private void loadEncyclopedia() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.ENCYCLOPEDIA);
    }

    @FXML
    private void loadStore() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.STORE);
    }

    @FXML
    private void loadLevel01() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.LEVEL_01);
    }

    @FXML
    private void loadSkillScene(){
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.SKILL);
    }

    // Private Method

    private void initializeSkillImage(){
        Image[] skillImages = ImageConfig.SKILL_ICONS;
        int[] equipedSkill = DataManager.getGameData().getEquipedSkill();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < equipedSkill.length; j ++){
                if(equipedSkill[i] == j){
                    skillIcons[i].setImage(skillImages[j]);
                }
            }
        }
    }

    private void initializeImage(){
        Forest.setImage(ImageConfig.FOREST);
        Graveyard.setImage(ImageConfig.GRAVEYARD);
        Bridge.setImage(ImageConfig.BRIDGE);
        Fish_Port.setImage(ImageConfig.FISH_PORT);
        Lake.setImage(ImageConfig.LAKE);
        Mountain.setImage(ImageConfig.MOUNTAIN);
        Tower.setImage(ImageConfig.TOWER);
        Dragon.setImage(ImageConfig.DRAGON);
        Portal.setImage(ImageConfig.MAP_PORTAL);
        gameBackground.setImage(ImageConfig.GAME_BACKGROUND);

        coinView.setImage(ImageConfig.COIN);
        characterView.setImage(ImageConfig.CHARACTER_ICON);
        storeView.setImage(ImageConfig.STORE_ICON);
        encyclopediaView.setImage(ImageConfig.ENCYCLOPEDIA_ICON);
        settingView.setImage(ImageConfig.SETTING_ICON);
    }


    private void setupSettingIconAnimation() {
        // Create the rotation animation
        settingRotationEnter = new RotateTransition(Duration.seconds(0.5), settingView);
        settingRotationEnter.setFromAngle(0);
        settingRotationEnter.setToAngle(90);
        settingRotationEnter.setCycleCount(1);

        settingRotationExit = new RotateTransition(Duration.seconds(0.5), settingView);
        settingRotationExit.setFromAngle(0);
        settingRotationExit.setToAngle(-90);
        settingRotationExit.setCycleCount(1);
    }
}
