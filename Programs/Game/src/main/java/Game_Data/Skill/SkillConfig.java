package Game_Data.Skill;

import Level.Data.Config.PlayerConfig;
import Level.Data.Suppliers.PlayerSupplier;
import Level.Skills.Skill;
import javafx.scene.image.Image;

public class SkillConfig {

    public static final Image ATTACK_ICON = new Image(SkillConfig.class.getResourceAsStream("/Skill_Icon/Cmp_Attack.png"));
    public static final Image DEFEND_ICON = new Image(SkillConfig.class.getResourceAsStream("/Skill_Icon/Cmp_Defend.png"));
    public static final Image DASH_ICON = new Image(SkillConfig.class.getResourceAsStream("/Skill_Icon/Dash.png"));
    public static final Image EMPTY_ICON = new Image(SkillConfig.class.getResourceAsStream("/Skill_Icon/Empty_Skill.png"));
    public static final Image[] SKILL_ICONS = new Image[]{
            EMPTY_ICON, ATTACK_ICON, DEFEND_ICON, DASH_ICON
    };

    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

    public static Skill getPlayerSkill(int skillID){
        return switch (skillID) {
            case ATTACK_SKILL_ID -> PlayerSupplier.getAttack();
            case DASH_SKILL_ID -> PlayerSupplier.getDash();
            case DEFEND_SKILL_ID -> PlayerSupplier.getDefend();
            default -> PlayerSupplier.getEmptySkill();
        };
    }
}
