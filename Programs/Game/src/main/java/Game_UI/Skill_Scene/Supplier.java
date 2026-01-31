package Game_UI.Skill_Scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.io.ObjectInputFilter;

public class Supplier {
    private static final Image Attack_Icon = new Image(
            Supplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Attack.png"));
    private static final Image Defend_Icon = new Image(
            Supplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Defend.png"));
    private static final Image Dash_Icon = new Image(
            Supplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Flash.png"));

    private static int ATTACK_SKILL_ID = 1;
    private static int DEFEND_SKILL_ID = 2;
    private static int DASH_SKILL_ID = 3;

    protected static void loadAttackSkill(VBox Skill_Vbox) {
        try {
            FXMLLoader loader = new FXMLLoader(Supplier.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(ATTACK_SKILL_ID, Attack_Icon);
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void loadDefendSkill(VBox Skill_Vbox) {
        try {
            FXMLLoader loader = new FXMLLoader(Supplier.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(DEFEND_SKILL_ID, Defend_Icon);
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static void loadDashSkill(VBox Skill_Vbox) {
        try {
            FXMLLoader loader = new FXMLLoader(Supplier.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml"));
            Node skillNode = loader.load();
            SkillController controller = loader.getController();
            controller.initializeData(DASH_SKILL_ID, Dash_Icon);
            Skill_Vbox.getChildren().add(skillNode);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
