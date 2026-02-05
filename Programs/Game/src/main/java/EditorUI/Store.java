package EditorUI;

import Game_Data.Config.SkillConfig;
import Game_Data.Interface.SceneInterface;
import Game_Data.Supplier.ImageLoader;
import Game_Data.Supplier.SceneLoader;
import Game_Data.Data.UpgradeText;
import Game_Data.Supplier.SkillSupplier;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.IOException;

public class Store extends SceneInterface {

    @FXML private Pane rootPane;
    @FXML private ImageView coinView, skillPointView;
    @FXML private Text coinLabel, skillPointLabel;
    @FXML private FlowPane upgradeFlowPane;
    @FXML private Pane displayPane;

    @FXML
    public void initialize() {
        coinView.setImage(ImageLoader.COIN);
    }

    @Override
    public void initializeData(){
        loadSkillPane();
        setupScrolling();
        coinLabel.setText(String.valueOf(fileManager.getGameFile().getCoins()));
    }

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    // Private Method

    private void loadSkillPane(){
        for (SkillConfig skillConfig : SkillSupplier.getSkillsConfig()){
            for(UpgradeText data: skillConfig.getUpgradeTexts()){
                try {
                    FXMLLoader loader = new FXMLLoader(Store.class.getResource(SceneLoader.PaneType.STORE_PANE.getPath()));
                    Node skillNode = loader.load();
                    StorePane storePane = loader.getController();
                    storePane.setFileManager(fileManager);
                    storePane.initializeData(data, coinLabel, upgradeFlowPane);
                    upgradeFlowPane.getChildren().add(skillNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setupScrolling() {
        rootPane.setOnScroll(event -> {

            if (upgradeFlowPane.getHeight() < (rootPane.getHeight() - displayPane.getLayoutY())) return;

            double deltaY = event.getDeltaY() * 1.5;
            double currentY = upgradeFlowPane.getTranslateY();
            double newY = currentY + deltaY;

            // Apply bounds
            double maxDown = displayPane.getHeight() - upgradeFlowPane.getHeight(); // Negative value
            newY = Math.max(maxDown, Math.min(0, newY));

            upgradeFlowPane.setTranslateY(newY);
            event.consume();
        });
    }

    private void returnButtonAnimation(Button btn, double targetY) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToY(targetY);
        tt.play();
    }
}
