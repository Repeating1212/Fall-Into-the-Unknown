package Data.Config;

public class SkillConfigList {

    private final EmptySkillConfig emptySkillConfig = new EmptySkillConfig();

    private final SkillConfig[] skillsConfig = new SkillConfig[]{
            emptySkillConfig, // Empty skill must always be first
            new AttackConfig(),
            new DashConfig(),
            new DefendConfig(),

    };

    public SkillConfigList(){
        // Initialize Skill ID
        for (int i = 0; i < skillsConfig.length; i ++ ){
            skillsConfig[i].setConfigID(i);
        }
    }

    public SkillConfig[] getSkillsConfig(){
        return skillsConfig;
    }

    public SkillConfig getEmptySkill(){
        return emptySkillConfig;
    }
}
