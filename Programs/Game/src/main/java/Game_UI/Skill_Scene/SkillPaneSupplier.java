package Game_UI.Skill_Scene;

import Game_Data.ImageLoader;
import Game_Data.SkillSupplier;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SkillPaneSupplier {

    protected static void loadAttackSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillSupplier.ATTACK_SKILL_ID,
                ImageLoader.ATTACK_ICON
        );
    }

    protected static void loadDefendSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillSupplier.DEFEND_SKILL_ID,
                ImageLoader.DEFEND_ICON
        );
    }

    protected static void loadDashSkill(VBox Skill_Vbox) {
        Skill_Pane controller = loadNewPane(Skill_Vbox);
        controller.initializeData(
                SkillSupplier.DASH_SKILL_ID,
                ImageLoader.DASH_ICON
        );
    }

    private static Skill_Pane loadNewPane(VBox Skill_VBox){
        try {
            FXMLLoader loader = new FXMLLoader(SkillPaneSupplier.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            Skill_VBox.getChildren().add(skillNode);
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return new Skill_Pane();
        }
    }
}
