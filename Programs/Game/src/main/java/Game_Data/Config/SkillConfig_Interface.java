package Game_Data.Config;

import Level.Skills.Skill;
import javafx.scene.image.Image;

public abstract class SkillConfig_Interface {

    public final int configID;
    public final Image image;

    public SkillConfig_Interface(int configID, Image image){
        this.configID = configID;
        this.image = image;
    }

    public int getConfigID(){
        return configID;
    }

    public abstract Skill getSkill();

    public Image getImage(){
        return image;
    }
}
