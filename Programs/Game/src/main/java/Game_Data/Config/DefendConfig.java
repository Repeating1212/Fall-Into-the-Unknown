package Game_Data.Config;

import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.Defend;
import LoadFile.DataManager;
import LoadFile.SkillFile.DefendFile;

public class DefendConfig extends SkillConfig_Interface{

    // Upgrades
    private static final double COOLDOWN_l1 = 6.0;
    private static final double COOLDOWN_l2 = 5.0;
    private static final double COOLDOWN_l3 = 4.5;

    private static final double DURATION_l1 = 1.0;
    private static final double DURATION_l2 = 1.5;
    private static final double DURATION_l3 = 2.0;

    public DefendConfig(){
        super(SkillConfig.DEFEND_SKILL_ID,
                ImageLoader.DEFEND_ICON);
    }

    public static final double[] COOLDOWN_UPG = new double[]{
            COOLDOWN_l1, COOLDOWN_l2, COOLDOWN_l3
    };
    public  static final double[] DURATION_UPG = new double[]{
            DURATION_l1, DURATION_l2, DURATION_l3
    };

    public Defend getSkill() {
        DefendFile defendFile = DataManager.getDefendFile();
        double cooldown = COOLDOWN_UPG[defendFile.getCooldownUpgrades()];
        double duration = DURATION_UPG[defendFile.getDurationUpgrade()];

        return new Defend(cooldown, duration);
    }
}
