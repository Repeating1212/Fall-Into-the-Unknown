package Game_Data.Config;

import Game_Data.Data.UpgradeData;
import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.EmptySkillFile;

public class EmptySkillConfig extends SkillConfig {

    public EmptySkillConfig(int configID){
        super(ImageLoader.EMPTY_ICON, configID, EmptySkillFile.class);
    }

    public Skill getSkill(FileManager fileManager){
        return new EmptySkill();
    }

    public final UpgradeData[] getUpgradeData(){
        return new UpgradeData[0];
    }
}
