package Data.SkillConfig;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Skills.EmptySkill;
import Level.BaseLevel.Skills.Skill;
import Game_File.FileManager;
import Game_File.SkillFile.EmptySkillFile;

public class EmptySkillConfig extends SkillConfig {

    public EmptySkillConfig(){
        super(
                ImageLoader.EMPTY_ICON,
                ImageLoader.EMPTY_ICON,
                EmptySkillFile.class,
                "Empty Skill");

        this.upgradeValues = new UpgradeValue[0];
    }

    public Skill getSkill(FileManager fileManager){
        return new EmptySkill();
    }

    public final UpgradeText[] getUpgradeTexts(){
        return new UpgradeText[0];
    }

    private final UpgradeText sampleText = new UpgradeText(
            "Empty Skill",
            "Description",
            "-",
            "",
            ImageLoader.EMPTY_ICON,
            new UpgradeValue(
                    null,
                    null,
                    1
            )
    );
}
