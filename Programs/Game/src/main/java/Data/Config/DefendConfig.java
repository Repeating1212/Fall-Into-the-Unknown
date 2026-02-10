package Data.Config;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Loader.ImageLoader;
import Level.Player.Skills.Player.Defend;
import LoadFile.FileManager;
import LoadFile.SkillFile.DefendFile;

public class DefendConfig extends SkillConfig {

    public DefendConfig(){
        super(ImageLoader.DEFEND_ICON,
                ImageLoader.DEFEND_ICON,
                DefendFile.class,
                "Defend");
        this.upgradeValues = new UpgradeValue[]{
                cooldownUpgValue, durationUpgValue
        };
    }

    public static final int COOLDOWN_ID = 1;
    public static final int DURATION_ID = 2;

    private final UpgradeValue cooldownUpgValue = new UpgradeValue(
            new double[]{ 6.0, 5.0, 4.5},
            new double[]{ 2, 4, 4},
            COOLDOWN_ID

    );

    private final UpgradeValue durationUpgValue = new UpgradeValue(
            new double[]{ 1.0, 1.5, 2.0},
            new double[]{ 2, 4, 4},
            DURATION_ID
    );

    private final UpgradeText durationUpg = new UpgradeText(
            "Defend", "Increase Duration", "Duration",
            "s", ImageLoader.DEFEND_ICON, durationUpgValue
    );

    private final UpgradeText cooldownUpg = new UpgradeText(
            "Defend", "Decrease Cooldown", "Cooldown",
            "s", ImageLoader.DEFEND_ICON, cooldownUpgValue
    );

    public final UpgradeText[] getUpgradeTexts(){
        return new UpgradeText[]{
                durationUpg, cooldownUpg
        };
    }

    public Defend getSkill(FileManager fileManager) {
        DefendFile defendFile = fileManager.getDefendFile();
        double cooldown = cooldownUpgValue.getValue(defendFile.getCooldownUpgrades());
        double duration = durationUpgValue.getValue(defendFile.getDurationUpgrade());

        return new Defend(cooldown, duration);
    }
}
