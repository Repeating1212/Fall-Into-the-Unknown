package Game_UI.Skill_Scene;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SkillScene {
    private Pane rootPane;
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
    }

    public void initializeData(Pane rootPane){
        this.rootPane = rootPane;
        SkillSupplier.loadAttackSkill(Skill_Vbox);
        SkillSupplier.loadDefendSkill(Skill_Vbox);
        SkillSupplier.loadDashSkill(Skill_Vbox);
    }

    @FXML
    private void handleReturn() {
        try {
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/Game_UI/GameScenes/GameScene/GameScene.fxml"));
            Scene startScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(startScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
