package Data.SkillConfig;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Skills.Dash;
import Game_File.FileManager;
import Game_File.SkillFile.DashFile;

public class DashConfig extends SkillConfig {

    // Upgrades
    public static final int RANGE_ID = 1;
    public static final int COOLDOWN_ID = 2;

    public DashConfig(){
        super(
                ImageLoader.DASH_ICON,
                ImageLoader.CMP_DASH_ICON,
                DashFile.class,
                "Dash"
        );

        this.upgradeValues = new UpgradeValue[]{
                rangeUpgValue, cooldownUpgValue
        };
    }

    private final UpgradeValue rangeUpgValue = new UpgradeValue(
            new double[]{ 150, 225, 300},
            new double[]{ 2, 4, 4},
            RANGE_ID
    );

    private final UpgradeValue cooldownUpgValue = new UpgradeValue(
            new double[]{ 5, 4, 3},
            new double[]{ 2, 4, 4},
            COOLDOWN_ID
    );

    private final UpgradeText rangeUpg = new UpgradeText(
            "Dash", "Increase Range", "Range",
            "", ImageLoader.DASH_ICON, rangeUpgValue
    );

    private final UpgradeText cooldownUpg = new UpgradeText(
            "Dash", "Decrease Cooldown", "Cooldown",
            "", ImageLoader.DASH_ICON, cooldownUpgValue
    );

    public final UpgradeText[] getUpgradeTexts(){
        return new UpgradeText[]{
                rangeUpg, cooldownUpg
        };
    }


    public Dash getSkill(FileManager fileManager){
        DashFile dashFile = fileManager.getDashFile();
        double range = rangeUpgValue.getValue(dashFile.getRangeUpgrades());
        double cooldown = cooldownUpgValue.getValue(dashFile.getCooldownUpgrades());
        return new Dash(cooldown, range);
    }
}
