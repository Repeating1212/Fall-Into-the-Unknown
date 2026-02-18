package Game_UI.GameScene;

import Data.Interface.SceneInterface;
import Data.Interface.SmokeScene;
import Data.Loader.ImageLoader;
import Data.Loader.SceneLoader;
import Data.Supplier.SkillSupplier;
import Game_UI.StartScene.GameController1;
import LoadFile.FileManager;
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

public class GameController2 extends SceneInterface implements SmokeScene {
    @FXML private ImageView Forest, Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal, gameBackground;
    @FXML private ImageView settingView, coinView;
    @FXML private ImageView characterView, encyclopediaView, storeView;
    @FXML private ImageView skill01, skill02, skill03, skill04;
    @FXML private Pane rootPane;
    @FXML private Text CoinLabel;

    @FXML private Pane stackPane;

    private RotateTransition settingRotationEnter;
    private RotateTransition settingRotationExit;

    private ImageView[] levels;
    private ImageView[] skillIcons;

    @FXML
    public void initialize() {
        levels = new  ImageView[]{Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal};
        skillIcons  = new ImageView[]{skill01, skill02, skill03, skill04};
        setupSettingIconAnimation();
    }

    @Override
    public void initializeData(){
        initializeImage();
        initializeSkillImage();
        for (ImageView level : levels) level.setOpacity(0.5);
        CoinLabel.setText(String.valueOf(fileManager.getGameFile().getCoins()));
    }

    @Override
    public void loadSmoke(){
        SceneLoader.loadOverlayScene(stackPane, SceneLoader.SceneType.SMOKE, fileManager);
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
        SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SETTING, fileManager);
    }

    @FXML
    private void loadCharacterState() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.CHARACTER, fileManager);
    }

    @FXML
    private void loadEncyclopedia() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.ENCYCLOPEDIA, fileManager);
    }

    @FXML
    private void loadStore() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.EQUIPMENT, fileManager);
    }

    @FXML
    private void loadLevel01() {
        SceneLoader.switchSceneWithSmoke(rootPane, SceneLoader.SceneType.LEVEL_01, fileManager);
    }

    @FXML
    private void loadSkillScene(){
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.EQUIPMENT, fileManager);
    }

    // Private Method

    private void initializeSkillImage(){
        Image[] images = SkillSupplier.getImages_UI(fileManager);
        for (int i = 0; i < 4; i++) {
            skillIcons[i].setImage(images[i]);
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
