package Game_UI.EquipSkill;

import Game_Data.Config.EmptySkillConfig;
import Game_Data.Config.SkillConfig;
import Game_Data.Config.SkillConfig_Interface;
import Game_Data.Supplier.SceneLoader;
import Game_Data.Supplier.SkillSupplier;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;

public class SkillScene {
    @FXML private Pane rootPane;
    @FXML private Button returnButton;
    @FXML private VBox Skill_Vbox;

    @FXML
    public void initialize() {
        returnButton.setOnMouseEntered(e -> {
            returnButtonAnimation(returnButton, 30);
        });
        returnButton.setOnMouseExited(e -> {
            returnButtonAnimation(returnButton, 0);
        });

        loadSkill();
    }

    @FXML
    private void handleReturn() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME);
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
        for (SkillConfig_Interface skillConfig : SkillSupplier.getSkillsConfig()){

            // Ignore Empty skill
            if(skillConfig.getClass() == EmptySkillConfig.class) continue;

            Skill_Pane controller = loadNewPane();
            controller.initializeData(
                    skillConfig.getConfigID(),
                    skillConfig.getImage()
            );
        }
    }

    private Skill_Pane loadNewPane(){
        try {
            FXMLLoader loader = new FXMLLoader(SkillScene.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            Skill_Vbox.getChildren().add(skillNode);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new Skill_Pane();
        }
    }
}
