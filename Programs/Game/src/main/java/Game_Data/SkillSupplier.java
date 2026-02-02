package Game_Data;

import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;

public class SkillSupplier {

    public static final int EMPTY_SKILL_ID = -1;
    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

    public static Skill getPlayerSkill(int skillID){
        return switch (skillID) {
            case ATTACK_SKILL_ID -> AttackConfig.getAttack();
            case DASH_SKILL_ID -> DashConfig.getDash();
            case DEFEND_SKILL_ID -> DefendConfig.getDefend();
            default -> new EmptySkill();
        };
    }
}
