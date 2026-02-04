package Game_Data.Config;

import Game_Data.Data.UpgradeData;
import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;

public class EmptySkillConfig extends SkillConfig {

    public EmptySkillConfig(int configID){
        super(ImageLoader.EMPTY_ICON, configID);
    }

    public Skill getSkill(){
        return new EmptySkill();
    }

    public final UpgradeData[] getUpgradeData(){
        return new UpgradeData[0];
    }
}
