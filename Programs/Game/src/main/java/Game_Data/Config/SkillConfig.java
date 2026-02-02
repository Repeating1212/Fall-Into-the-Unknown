package Game_Data.Config;

import Level.Data.Suppliers.PlayerSupplier;
import Level.Skills.Skill;

public class SkillConfig {

    public static final int EMPTY_SKILL_ID = -1;
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
