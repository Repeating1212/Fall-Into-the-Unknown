package Game_UI.Icons_Scene;

import Game_Data.DataManager;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class StoreScene {
    private Pane rootPane;
    @FXML private Button returnButton;
    @FXML private Button attackEquipButton, defendEquipButton, dashEquipButton;

    @FXML
    public void initialize() {
        returnButton.setOnMouseEntered(e -> {
            returnButtonAnimation(returnButton, 30);
        });
        returnButton.setOnMouseExited(e -> {
            returnButtonAnimation(returnButton, 0);
        });
        updateSkillText();
    }

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }


    @FXML
    private void handleReturn() {
        try {
            // Reload the FXML
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
    private void equipAttackSkill() {
        DataManager.getSkillData().setAttackEquip(!DataManager.getSkillData().isAttackEquip());
        updateSkillText();
    }

    @FXML
    private void equipDefendSkill() {
        DataManager.getSkillData().setDefendEquip(!DataManager.getSkillData().isDefendEquip());
        updateSkillText();
    }

    @FXML
    private void equipDashSkill() {
        DataManager.getSkillData().setDashEquip(!DataManager.getSkillData().isDashEquip());
        updateSkillText();
    }

    // Private Method

    private void returnButtonAnimation(Button btn, double targetY) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToY(targetY);
        tt.play();
    }

    private void updateSkillText(){
        if (DataManager.getSkillData().isAttackEquip()){
            attackEquipButton.setText("Equiped");
        } else{
            attackEquipButton.setText("Removed");
        }
        if (DataManager.getSkillData().isDefendEquip()){
            defendEquipButton.setText("Equiped");
        } else{
            defendEquipButton.setText("Removed");
        }
        if (DataManager.getSkillData().isDashEquip()){
            dashEquipButton.setText("Equiped");
        } else{
            dashEquipButton.setText("Removed");
        }
    }
}
