package Data.Config;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Loader.ImageLoader;
import Level.Player.Skills.Player.EmptySkill;
import Level.Player.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.EmptySkillFile;

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
