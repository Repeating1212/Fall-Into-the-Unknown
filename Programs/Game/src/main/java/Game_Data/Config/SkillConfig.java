package Game_Data.Config;

import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;

public class SkillConfig {

    public static final AttackConfig attackConfig = new AttackConfig();
    public static final DashConfig dashConfig = new DashConfig();
    public static final DefendConfig defendConfig = new DefendConfig();
    public static final EmptySkillConfig emptySkill = new EmptySkillConfig();

    public static final int EMPTY_SKILL_ID = 0;
    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

    public static final SkillConfig_Interface[] skillsConfig = new SkillConfig_Interface[]{
            emptySkill,
            attackConfig,
            defendConfig,
            dashConfig,
    };

    public static int[] initialSkill(){
        return new int[]{
                SkillConfig.ATTACK_SKILL_ID,
                SkillConfig.EMPTY_SKILL_ID,
                SkillConfig.EMPTY_SKILL_ID,
                SkillConfig.EMPTY_SKILL_ID
        };
    }
}
