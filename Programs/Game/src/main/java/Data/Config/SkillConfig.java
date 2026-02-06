package Data.Config;

import Data.DataClass.UpgradeText;
import Level.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.SkillFile;
import javafx.scene.image.Image;

public abstract class SkillConfig {

    public final int CONFIG_ID;
    public final Image IMAGE;
    public final String NAME;
    protected final Class<? extends SkillFile> fileType;

    public SkillConfig(Image IMAGE, int configID, Class<? extends SkillFile> skillFile, String NAME){
        this.IMAGE = IMAGE;
        this.CONFIG_ID = configID;
        this.fileType = skillFile;
        this.NAME = NAME;
    }

    public abstract Skill getSkill(FileManager fileManager);

    public abstract UpgradeText[] getUpgradeTexts();

    public Class<? extends SkillFile> getFileType() {
        return fileType;
    }
}
