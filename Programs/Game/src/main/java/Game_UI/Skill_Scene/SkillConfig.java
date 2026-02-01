package Game_UI.Skill_Scene;

import javafx.scene.image.Image;

public class SkillConfig {

    public static final Image ATTACK_ICON = new Image(
            SkillSupplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Attack.png"));
    public static final Image DEFEND_ICON = new Image(
            SkillSupplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Cmp_Defend.png"));
    public static final Image DASH_ICON = new Image(
            SkillSupplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Dash.png"));
    public static final Image EMPTY_ICON = new Image(
            SkillSupplier.class.getResourceAsStream("/Game_UI/Icons_Scene/SkillScene/Icons/Empty_Skill.png"));
    public static final Image[] SKILL_ICONS = new Image[]{
            EMPTY_ICON, ATTACK_ICON, DEFEND_ICON, DASH_ICON
    };

    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

}
