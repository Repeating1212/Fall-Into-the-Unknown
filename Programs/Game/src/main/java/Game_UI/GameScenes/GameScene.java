package Game_UI.GameScenes;

import Game_Data.ImageLoader;
import Game_Data.SceneLoader;
import LoadFile.DataManager;
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

        DataManager.loadFile();
        initializeImage();
        initializeSkillImage();

        for (ImageView level : levels) level.setOpacity(0.5);
        setupSettingIconAnimation();
        CoinLabel.setText(String.valueOf(DataManager.getGameFile().getCoins()));

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
        Image[] skillImages = ImageLoader.SKILL_ICONS;
        int[] equipedSkill = DataManager.getGameFile().getEquipedSkill();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < equipedSkill.length; j ++){
                if(equipedSkill[i] == j){
                    skillIcons[i].setImage(skillImages[j]);
                }
            }
        }
    }

    private void initializeImage(){
        Forest.setImage(ImageLoader.FOREST);
        Graveyard.setImage(ImageLoader.GRAVEYARD);
        Bridge.setImage(ImageLoader.BRIDGE);
        Fish_Port.setImage(ImageLoader.FISH_PORT);
        Lake.setImage(ImageLoader.LAKE);
        Mountain.setImage(ImageLoader.MOUNTAIN);
        Tower.setImage(ImageLoader.TOWER);
        Dragon.setImage(ImageLoader.DRAGON);
        Portal.setImage(ImageLoader.MAP_PORTAL);
        gameBackground.setImage(ImageLoader.GAME_BACKGROUND);

        coinView.setImage(ImageLoader.COIN);
        characterView.setImage(ImageLoader.CHARACTER_ICON);
        storeView.setImage(ImageLoader.STORE_ICON);
        encyclopediaView.setImage(ImageLoader.ENCYCLOPEDIA_ICON);
        settingView.setImage(ImageLoader.SETTING_ICON);
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
