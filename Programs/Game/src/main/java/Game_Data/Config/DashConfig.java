package Game_Data.Config;

import Game_Data.Supplier.ImageLoader;
import Level.Skills.Player.Dash;
import LoadFile.DataManager;
import LoadFile.SkillFile.DashFile;

public class DashConfig extends SkillConfig_Interface{

    // Upgrades
    private static final double RANGE_l1 = 150;
    private static final double RANGE_l2 = 225;
    private static final double RANGE_l3 = 300;

    private static final double COOLDOWN_l1 = 5;
    private static final double COOLDOWN_l2 = 4;
    private static final double COOLDOWN_l3 = 3;

    public DashConfig(){
        super(SkillConfig.DASH_SKILL_ID,
                ImageLoader.DASH_ICON);
    }

    public static final double[] RANGE_UPG = new double[]{
            RANGE_l1, RANGE_l2, RANGE_l3
    };
    public static final double[] COOLDOWN_UPG = new double[]{
            COOLDOWN_l1, COOLDOWN_l2, COOLDOWN_l3
    };

    public Dash getSkill(){
        DashFile dashFile = DataManager.getDashFile();
        double range = RANGE_UPG[dashFile.getRangeUpgrades()];
        double cooldown = COOLDOWN_UPG[dashFile.getCooldownUpgrades()];
        return new Dash(cooldown, range);
    }
}
