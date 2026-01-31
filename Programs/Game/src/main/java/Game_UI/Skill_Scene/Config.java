//package Game_UI.Skill_Scene;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
//import javafx.scene.image.Image;
//
//import java.io.IOException;
//
//public class Config {
//
//    public static void loadAttackSkill() {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    Config.class.getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
//            );
//            Image Attack_Icon = new Image(Config.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Attack.png"));
//
//            // Load the FXML content (could be any Node: Button, Pane, etc.)
//            Node skillNode = loader.load();
//            SkillController controller = loader.getController();
//            controller.initializeData(1, Attack_Icon);
//
//            // Add to VBox
//            Skill_Vbox.getChildren().add(skillNode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void loadDefendSkill() {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
//            );
//            Image Defend_Icon = new Image(getClass().getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Defend.png"));
//
//            // Load the FXML content (could be any Node: Button, Pane, etc.)
//            Node skillNode = loader.load();
//            SkillController controller = loader.getController();
//            controller.initializeData(2, Defend_Icon);
//
//            // Add to VBox
//            Skill_Vbox.getChildren().add(skillNode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//    private void loadDashSkill() {
//        try {
//            FXMLLoader loader = new FXMLLoader(
//                    getClass().getResource("/Game_UI/Icons_Scene/SkillScene/SkillDisplay.fxml")
//            );
//            Image Dash_Icon = new Image(getClass().getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Flash.png"));
//
//            Node skillNode = loader.load();
//            SkillController controller = loader.getController();
//            controller.initializeData(3, Dash_Icon);
//
//            // Add to VBox
//            Skill_Vbox.getChildren().add(skillNode);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
