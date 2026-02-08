package Game_UI.EquipmentScene;

import Data.Config.EmptySkillConfig;
import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Interface.SceneInterface;
import Data.Loader.ImageLoader;
import Data.Loader.SceneLoader;
import Data.Supplier.SkillSupplier;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class Equipment extends SceneInterface {
    @FXML
    private Pane rootPane;
    @FXML private ImageView coinView;
    @FXML private Text coinLabel, equipmentText;
    @FXML private FlowPane flowPane;
    @FXML private HBox hBox;
    @FXML private Button returnButton;

    private final EquipmentObserver observer = new EquipmentObserver(this);

    @FXML
    public void initialize() {
        coinView.setImage(ImageLoader.COIN);
        initializeAnimation();
    }

    @Override
    public void initializeData(){
        loadSkill();
        loadEquipment();
        coinLabel.setText(String.valueOf(fileManager.getGameFile().getCoins()));
    }

    public void reloadData(){
        flowPane.getChildren().clear();
        hBox.getChildren().clear();
        loadSkill();
        loadEquipment();
        coinLabel.setText(String.valueOf(fileManager.getGameFile().getCoins()));
    }

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    // Private Method

    private void loadSkill(){
        for (SkillConfig skillConfig : SkillSupplier.getSkillsConfig()){

            // Ignore Empty skill
            if(skillConfig.getClass() == EmptySkillConfig.class) continue;

            SkillPane controller = loadSkillPane();
            assert controller != null;
            controller.setData(skillConfig, observer);
        }
    }

    private SkillPane loadSkillPane(){
        try {
            FXMLLoader loader = new FXMLLoader(Equipment.class.getResource(SceneLoader.PaneType.SKILL_PANE.getPath()));
            Node skillNode = loader.load();
            flowPane.getChildren().add(skillNode);

            Object controller = loader.getController();
            if (controller instanceof PaneInterface) {
                ((PaneInterface) controller).setFileManager(fileManager);
                ((PaneInterface) controller).setRootPane(rootPane);
                ((PaneInterface) controller).initializeData();
            }

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new SkillPane();
        }
    }

    private void loadEquipment(){
        for (int skillID : fileManager.getGameFile().getEquipment()){
            SkillConfig skillConfig = SkillSupplier.getSkillConfig(skillID);
            // Ignore if it is Empty Skill
            if (skillConfig.getClass() == EmptySkillConfig.class) continue;

            EquipmentPane controller = loadEquipmentPane();
            assert controller != null;
            controller.setData(skillID, observer);
        }

        equipmentText.setText(
                "Equiment: (" + hBox.getChildren().size() +"/4)"
        );
    }

    private EquipmentPane loadEquipmentPane(){
        try {
            FXMLLoader loader = new FXMLLoader(Equipment.class.getResource(SceneLoader.PaneType.EQUIPMENT_PANE.getPath()));
            Node equipmentNode = loader.load();
            hBox.getChildren().add(equipmentNode);

            Object controller = loader.getController();
            if (controller instanceof PaneInterface) {
                ((PaneInterface) controller).setFileManager(fileManager);
                ((PaneInterface) controller).setRootPane(rootPane);
                ((PaneInterface) controller).initializeData();
            }

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new EquipmentPane();
        }
    }

    private void initializeAnimation() {
        TranslateTransition hoverUp = new TranslateTransition(Duration.millis(200), returnButton);
        hoverUp.setToY(10);

        TranslateTransition hoverDown = new TranslateTransition(Duration.millis(200), returnButton);
        hoverDown.setToY(0);

        returnButton.setOnMouseEntered(e -> {
            hoverDown.stop(); // Stop returning if it's running
            hoverUp.playFromStart();
        });

        returnButton.setOnMouseExited(e -> {
            hoverUp.stop(); // Stop moving up if it's running
            hoverDown.playFromStart();
        });
    }
}
