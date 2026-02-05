package Game_Data.Supplier;

import Game_Data.Config.*;
import LoadFile.SkillFile.SkillFile;

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

//    public SkillConfigList(SkillFile[] skillFiles){
//        for (SkillConfig skillConfig :skillsConfig){
//            for (SkillFile skillFile : skillFiles){
//                if (skillConfig.getFileType() == skillFile.getClass()){
//
//                }
//            }
//        }
//    }

    public SkillConfig[] getSkillsConfig() {
        return skillsConfig;
    }

    public SkillConfig getSkillConfig(int skillID){
        for (SkillConfig skillConfig : skillsConfig){
            if (skillConfig.getConfigID() == skillID){
                return skillConfig;
            }
        }
        return new EmptySkillConfig(EMPTY_SKILL_ID);
    }
}
