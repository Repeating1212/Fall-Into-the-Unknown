package Data.SkillConfig;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Level.BaseLevel.Skills.Skill;
import Game_File.FileManager;
import Game_File.SkillFile.SkillFile;
import javafx.scene.image.Image;

public abstract class SkillConfig {

    protected int configID;
    public final Image UI_IMAGE;
    public final Image LEVEL_IMAGE;
    public final String NAME;
    protected final Class<? extends SkillFile> fileType;
    protected UpgradeValue[] upgradeValues;

    public SkillConfig(Image IMAGE, Image LEVEL_IMAGE, Class<? extends SkillFile> skillFile, String NAME){
        this.UI_IMAGE = IMAGE;
        this.LEVEL_IMAGE = LEVEL_IMAGE;
        this.fileType = skillFile;
        this.NAME = NAME;
    }

    public abstract Skill getSkill(FileManager fileManager);

    public abstract UpgradeText[] getUpgradeTexts();

    public Class<? extends SkillFile> getFileType() {
        return fileType;
    }

    public int getConfigID(){
        return configID;
    }

    public void setConfigID(int configID){
        this.configID = configID;
        for (UpgradeValue upgradeValue : upgradeValues){
            upgradeValue.setSkillID(configID);
        }
    }
}
