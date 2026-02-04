package Game_Data.Config;

import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;

public class EmptySkillConfig extends SkillConfig_Interface{

    public EmptySkillConfig(){
        super(ImageLoader.EMPTY_ICON);
    }

    public Skill getSkill(){
        return new EmptySkill();
    }
}
