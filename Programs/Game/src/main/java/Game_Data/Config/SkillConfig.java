package Game_Data.Config;

public class SkillConfig {

    public static final AttackConfig attackConfig = new AttackConfig();
    public static final int EMPTY_SKILL_ID = 0;
    public static final int ATTACK_SKILL_ID = 1;

    private final SkillConfig_Interface[] skillsConfig = new SkillConfig_Interface[]{
            new EmptySkillConfig(),
            new AttackConfig(),
            new DefendConfig(),
            new DashConfig(),
    };

    public SkillConfig(){
        for (int i = 0; i < skillsConfig.length; i++){
            skillsConfig[i].initializeID(i);
        }
    }

    public SkillConfig_Interface[] getSkillsConfig() {
        return skillsConfig;
    }

    public int[] initialSkill(){
        return new int[] {1,0,0,0};
//        return new int[]{
//                attackConfig.getConfigID(),
//                emptySkill.getConfigID(),
//                emptySkill.getConfigID(),
//                emptySkill.getConfigID(),
//        };
    }
}
