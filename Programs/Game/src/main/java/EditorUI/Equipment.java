package EditorUI;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Interface.SceneInterface;
import Data.Supplier.SceneLoader;
import Data.Supplier.SkillSupplier;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.io.IOException;

public class Equipment extends SceneInterface {
    @FXML private Pane rootPane;
    @FXML private Button returnButton;
    @FXML private FlowPane flowPane;
    @FXML private Pane displayPane;

    @FXML
    public void initialize() {
        returnButton.setOnMouseEntered(e -> {
            returnButtonAnimation(returnButton, 30);
        });
        returnButton.setOnMouseExited(e -> {
            returnButtonAnimation(returnButton, 0);
        });
    }

    @Override
    public void initializeData(){
        loadSkill();
        setupScrolling();
    }

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    @FXML
    private void skillButton_MouseEnter(MouseEvent event){
        Button btn = (Button) event.getSource();
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToX(20);
        tt.play();
    }

    @FXML
    private void skillButton_MouseExit(MouseEvent event){
        Button btn = (Button) event.getSource();
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToX(0);
        tt.play();
    }

    // Private Method

    private void returnButtonAnimation(Button btn, double targetY) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToY(targetY);
        tt.play();
    }

    private void loadSkill(){
        for (SkillConfig skillConfig : SkillSupplier.getSkillsConfig()){

            // Ignore Empty skill
//            if(skillConfig.getClass() == EmptySkillConfig.class) continue;

            EquipmentPane controller = loadEquipmentPane();
            assert controller != null;
            controller.setData(
                    skillConfig.CONFIG_ID,
                    skillConfig.IMAGE
            );
        }
    }

    private EquipmentPane loadEquipmentPane(){
        try {
            FXMLLoader loader = new FXMLLoader(Equipment.class.getResource(SceneLoader.PaneType.EQUIPMENT_PANE.getPath()));
            Node skillNode = loader.load();
            flowPane.getChildren().add(skillNode);

            Object controller = loader.getController();
            if (controller instanceof PaneInterface) {
                ((PaneInterface) controller).setFileManager(fileManager);
                ((PaneInterface) controller).initializeData();
            }

            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new EquipmentPane();
        }
    }

    private void setupScrolling() {
        rootPane.setOnScroll(event -> {

            if (flowPane.getHeight() < (rootPane.getHeight() - displayPane.getLayoutY())) return;

            double deltaY = event.getDeltaY() * 1.5;
            double currentY = flowPane.getTranslateY();
            double newY = currentY + deltaY;

            // Apply bounds
            double maxDown = displayPane.getHeight() - flowPane.getHeight(); // Negative value
            newY = Math.max(maxDown, Math.min(0, newY));

            flowPane.setTranslateY(newY);
            event.consume();
        });
    }
}
