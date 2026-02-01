package Game_UI.Skill_Scene;

import Game_Data.Config.SkillConfig;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SkillSupplier {

    protected static void loadAttackSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillConfig.ATTACK_SKILL_ID,
                SkillConfig.ATTACK_ICON
        );
    }

    protected static void loadDefendSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillConfig.DEFEND_SKILL_ID,
                SkillConfig.DEFEND_ICON
        );
    }

    protected static void loadDashSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillConfig.DASH_SKILL_ID,
                SkillConfig.DASH_ICON
        );
    }

    private static Skill_Pane loadNewPane(VBox Skill_VBox){
        try {
            FXMLLoader loader = new FXMLLoader(SkillSupplier.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            Skill_VBox.getChildren().add(skillNode);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new Skill_Pane();
        }
    }
}
