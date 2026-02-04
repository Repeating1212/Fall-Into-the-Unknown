package Game_Data.Config;

import Level.Skills.Skill;
import javafx.scene.image.Image;

public abstract class SkillConfig_Interface {

    protected int configID;
    protected final Image image;

    public SkillConfig_Interface(Image image){
        this.image = image;
    }

    public int getConfigID(){
        return configID;
    }

    public abstract Skill getSkill();

    public Image getImage(){
        return image;
    }

    public void initializeID(int configID){
        this.configID = configID;
    }
}
