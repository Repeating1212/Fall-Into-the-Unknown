package Game_UI.Skill_Scene;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SkillScene {
    private Pane rootPane;
    @FXML private Button returnButton;
    @FXML private Button skill01Btn ,skill02Btn, skill03Btn, skill04Btn;
    @FXML private VBox Skill_Vbox;

    private int currentSkillSelection = 1;
    private Button[] skillButtons;

    @FXML
    public void initialize() {
        returnButton.setOnMouseEntered(e -> {
            returnButtonAnimation(returnButton, 30);
        });
        returnButton.setOnMouseExited(e -> {
            returnButtonAnimation(returnButton, 0);
        });
        skillButtons = new Button[]{skill01Btn, skill02Btn, skill03Btn, skill04Btn};
        loadAttackSkill();
        loadDefendSkill();
        loadDashSkill();
    }

    public void setCurrentSkillSelection(int currentSkillSelection){
        this.currentSkillSelection = currentSkillSelection;
        updateSkillSelection();
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
    private void skillButton_MouseEnter(MouseEvent event){
        Button btn = (Button) event.getSource();
        if (btn == currentSkillSelectionBtn()) return;
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToX(20);
        tt.play();
    }

    @FXML
    private void skillButton_MouseExit(MouseEvent event){
        Button btn = (Button) event.getSource();
        if (btn == currentSkillSelectionBtn()) return;
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToX(0);
        tt.play();
    }

    @FXML
    private void updateSkillPage(MouseEvent event){
        Button clickedButton = (Button) event.getSource();
        if (clickedButton== skill01Btn) setCurrentSkillSelection(1);
        if (clickedButton == skill02Btn) setCurrentSkillSelection(2);
        if (clickedButton == skill03Btn) setCurrentSkillSelection(3);
        if (clickedButton == skill04Btn) setCurrentSkillSelection(4);
    }

    // Private Method

    private void returnButtonAnimation(Button btn, double targetY) {
        TranslateTransition tt = new TranslateTransition(
                Duration.millis(200), btn);
        tt.setToY(targetY);
        tt.play();
    }

    private void updateSkillSelection(){
        Button currentSkillSelectionBtn = currentSkillSelectionBtn();
        for (Button button : skillButtons){
            TranslateTransition tt = new TranslateTransition(Duration.millis(200), button);
            if (button == currentSkillSelectionBtn) {
                tt.setToX(20);
            } else {
                tt.setToX(0);
            }
            tt.play();
        }
    }

    private Button currentSkillSelectionBtn(){
        return switch (currentSkillSelection) {
            case 1 -> skill01Btn;
            case 2 -> skill02Btn;
            case 3 -> skill03Btn;
            case 4 -> skill04Btn;
            default -> skill02Btn;
        };
    }

    private void loadAttackSkill() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
            );
            Image Attack_Icon = new Image(getClass().getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Attack.png"));

            // Load the FXML content (could be any Node: Button, Pane, etc.)
            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(1, Attack_Icon);

            // Add to VBox
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDefendSkill() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
            );
            Image Defend_Icon = new Image(getClass().getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Defend.png"));

            // Load the FXML content (could be any Node: Button, Pane, etc.)
            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(2, Defend_Icon);

            // Add to VBox
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDashSkill() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
            );
            Image Dash_Icon = new Image(getClass().getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Flash.png"));

            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(3, Dash_Icon);

            // Add to VBox
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
