package Data.Config;

import Data.DataClass.UpgradeText;
import Level.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.SkillFile;
import javafx.scene.image.Image;

public abstract class SkillConfig {

    protected final int configID;
    protected final Image image;
    protected final Class<? extends SkillFile> fileType;

    public SkillConfig(Image image, int configID, Class<? extends SkillFile> skillFile){
        this.image = image;
        this.configID = configID;
        this.fileType = skillFile;
    }

    public int getConfigID(){
        return configID;
    }

    public abstract Skill getSkill(FileManager fileManager);

    public abstract UpgradeText[] getUpgradeTexts();

    public Image getImage(){
        return image;
    }

    public Class<? extends SkillFile> getFileType() {
        return fileType;
    }
}
