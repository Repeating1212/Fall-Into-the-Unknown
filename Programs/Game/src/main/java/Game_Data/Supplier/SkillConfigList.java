package Game_Data.Supplier;

import Game_Data.Config.*;

public class SkillConfigList {

    public static final int EMPTY_SKILL_ID = 0;
    public static final int ATTACK_ID = 1;
    public static final int DEFEND_ID = 2;
    public static final int DASH_ID = 3;

    private final SkillConfig[] skillsConfig = new SkillConfig[]{
            new EmptySkillConfig(EMPTY_SKILL_ID),
            new AttackConfig(ATTACK_ID),
            new DefendConfig(DEFEND_ID),
            new DashConfig(DASH_ID),
    };

    protected SkillConfigList(){}

    public SkillConfig[] getSkillsConfig() {
        return skillsConfig;
    }

    public int[] initialSkill(){
        int attackID = 0;
        int emptyID = 0;
        for (SkillConfig skillConfig: skillsConfig){
            if(skillConfig.getClass() == AttackConfig.class){
                attackID = skillConfig.getConfigID();
            }
            if (skillConfig.getClass() == EmptySkillConfig.class){
                emptyID = skillConfig.getConfigID();
            }
        }

        return new int[] {
                attackID,
                emptyID,
                emptyID,
                emptyID,
        };
    }
}
