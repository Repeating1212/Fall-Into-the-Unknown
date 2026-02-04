package Game_Data.Config;

import Game_Data.Data.UpgradeData;
import Level.Skills.Skill;
import javafx.scene.image.Image;

public abstract class SkillConfig {

    protected final int configID;
    protected final Image image;
    protected UpgradeData[] upgradeData;

    public SkillConfig(Image image, int configID){
        this.image = image;
        this.configID = configID;
    }

    public int getConfigID(){
        return configID;
    }

    public abstract Skill getSkill();

    public abstract UpgradeData[] getUpgradeData();

    public Image getImage(){
        return image;
    }
}
