package Game_Data.Config;

import Game_Data.Data.UpgradeData;
import Game_Data.Data.UpgradeValue;
import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.Defend;
import LoadFile.DataManager;
import LoadFile.SkillFile.DefendFile;

public class DefendConfig extends SkillConfig {

    public DefendConfig(int configID){
        super(ImageLoader.DEFEND_ICON, configID);
    }

    public static final int COOLDOWN_ID = 1;
    public static final int DURATION_ID = 2;

    private final UpgradeValue COOLDOWN = new UpgradeValue(
            new double[]{ 6.0, 5.0, 4.5},
            new double[]{ 2, 4, 4},
            configID,
            COOLDOWN_ID

    );

    private final UpgradeValue DURATION = new UpgradeValue(
            new double[]{ 1.0, 1.5, 2.0},
            new double[]{ 2, 4, 4},
            configID,
            DURATION_ID
    );

    private final UpgradeData durationUpg = new UpgradeData(
            "Defend", "Increase Duration", "Duration",
            "s", ImageLoader.DEFEND_ICON, DURATION
    );

    private final UpgradeData cooldownUpg = new UpgradeData(
            "Defend", "Decrease Cooldown", "Cooldown",
            "s", ImageLoader.DEFEND_ICON, COOLDOWN
    );

    public final UpgradeData[] getUpgradeData(){
        return new UpgradeData[]{
                durationUpg, cooldownUpg
        };
    }

    public Defend getSkill() {
        DefendFile defendFile = DataManager.getDefendFile();
        double cooldown = COOLDOWN.getValue(defendFile.getCooldownUpgrades());
        double duration = DURATION.getValue(defendFile.getDurationUpgrade());

        return new Defend(cooldown, duration);
    }
}
