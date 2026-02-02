package Game_UI.Skill_Scene;

import Game_Data.SceneLoader;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

        SkillPaneSupplier.loadAttackSkill(Skill_Vbox);
        SkillPaneSupplier.loadDefendSkill(Skill_Vbox);
        SkillPaneSupplier.loadDashSkill(Skill_Vbox);
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
}
