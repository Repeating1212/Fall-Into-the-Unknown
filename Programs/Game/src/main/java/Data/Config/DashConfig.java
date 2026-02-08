package Data.Config;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Loader.ImageLoader;
import Level.Skills.Player.Dash;
import LoadFile.FileManager;
import LoadFile.SkillFile.DashFile;

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
    }

    private final UpgradeValue RANGE = new UpgradeValue(
            new double[]{ 150, 225, 300},
            new double[]{ 2, 4, 4},
            configID,
            RANGE_ID
    );

    private final UpgradeValue COOLDOWN = new UpgradeValue(
            new double[]{ 5, 4, 3},
            new double[]{ 2, 4, 4},
            configID,
            COOLDOWN_ID
    );

    private final UpgradeText rangeUpg = new UpgradeText(
            "Dash", "Increase Range", "Range",
            "", ImageLoader.DASH_ICON, RANGE
    );

    private final UpgradeText cooldownUpg = new UpgradeText(
            "Dash", "Decrease Cooldown", "Cooldown",
            "", ImageLoader.DASH_ICON, COOLDOWN
    );

    public final UpgradeText[] getUpgradeTexts(){
        return new UpgradeText[]{
                rangeUpg, cooldownUpg
        };
    }


    public Dash getSkill(FileManager fileManager){
        DashFile dashFile = fileManager.getDashFile();
        double range = RANGE.getValue(dashFile.getRangeUpgrades());
        double cooldown = COOLDOWN.getValue(dashFile.getCooldownUpgrades());
        return new Dash(cooldown, range);
    }
}
