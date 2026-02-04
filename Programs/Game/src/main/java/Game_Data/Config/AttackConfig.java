package Game_Data.Config;

import Game_Data.Supplier.ImageLoader;
import Game_Data.Data.UpgradeData;
import Game_Data.Data.UpgradeValue;
import Level.Skills.Attacks.AttackArea;
import Level.Skills.Attacks.AttackSkill;
import Level.Skills.Attacks.ConeAttackArea;
import Level.View.AttackVisualize.ConeAttackVisual;
import LoadFile.DataManager;
import LoadFile.SkillFile.AttackFile;
import javafx.scene.image.Image;

public class AttackConfig extends SkillConfig_Interface{

    public static final int SKILL_ID = SkillConfig.ATTACK_SKILL_ID;
    public static final Image skillImage = ImageLoader.ATTACK_ICON;

    public static final int DAMAGE_ID = 1;
    public static final int RANGE_ID = 2;
    public static final int COOLDOWN_ID = 3;
    public static final int ATTACK_ANGLE_ID = 4;

    public AttackConfig(){
        super(SKILL_ID, skillImage);
    }

    // AttackConfig Animation
    private static final double ENLARGE_DURATION = 0.2;
    private static final double FADE_OUT_DURATION = 0.1;
    private static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    private static final double RIGID_TIME = TOTAL_ANIMATION_PERIOD * 2.0;

    private static final UpgradeValue DAMAGE = new UpgradeValue(
            new double[]{ 6, 12, 18},
            new double[]{ 2, 4, 4},
            SKILL_ID,
            DAMAGE_ID
    );

    private static final UpgradeValue RANGE = new UpgradeValue(
            new double[]{ 100, 120, 140},
            new double[]{ 4, 4, 4},
            SKILL_ID,
            RANGE_ID
    );

    private static final UpgradeValue COOLDOWN = new UpgradeValue(
            new double[]{ 2.0, 1.5, 1.0},
            new double[]{ 4, 8, 8},
            SKILL_ID,
            COOLDOWN_ID
    );

    private static final UpgradeValue ATTACK_ANGLE = new UpgradeValue(
            new double[]{ 60, 75, 90},
            new double[]{ 4, 8, 8},
            SKILL_ID,
            ATTACK_ANGLE_ID
    );

    private static final UpgradeData damageUpg = new UpgradeData(
            "Attack", "Increase Damage", "Damage",
            "", ImageLoader.ATTACK_ICON, DAMAGE
    );

    private static final UpgradeData rangeUpg = new UpgradeData(
            "Attack", "Increase Range", "Range",
            "", ImageLoader.ATTACK_ICON, RANGE
    );

    private static final UpgradeData cooldownUpg = new UpgradeData(
            "Attack", "Decrease Cooldown", "Cooldown",
            "s", ImageLoader.ATTACK_ICON, COOLDOWN
    );

    private static final UpgradeData angleUpg = new UpgradeData(
            "Attack", "Increase Attack Angle", "Attack Angle",
            "\u00B0", ImageLoader.ATTACK_ICON, ATTACK_ANGLE
    );

    // Public Method

    public static final UpgradeData[] upgradeData = new UpgradeData[]{
            damageUpg, rangeUpg, cooldownUpg, angleUpg
    };

    public static int damageUpgLength(){
        return DAMAGE.getTotalUpgrade();
    }
    public static int rangeUpgLength(){
        return RANGE.getTotalUpgrade();
    }
    public static int cooldownUpgLength(){
        return COOLDOWN.getTotalUpgrade();
    }
    public static int angleUpgLength(){
        return ATTACK_ANGLE.getTotalUpgrade();
    }


    public AttackSkill getSkill(){
        AttackFile attackFile = DataManager.getAttackFile();
        double damage = DAMAGE.getValue(attackFile.getDamageUpgrade());
        double cooldown = COOLDOWN.getValue(attackFile.getCooldownUpgrade());
        double range = RANGE.getValue(attackFile.getRangeUpgrade());
        double areaAngle = ATTACK_ANGLE.getValue(attackFile.getAreaAngleUpgrade());

        AttackArea attackArea = new ConeAttackArea(
                range, areaAngle
        );

        ConeAttackVisual attackVisual = new ConeAttackVisual(
                range, areaAngle, ENLARGE_DURATION, FADE_OUT_DURATION
        );

        AttackSkill attackSkill = new AttackSkill(
                attackVisual,
                AttackConfig.RIGID_TIME,
                attackArea,
                damage,
                cooldown,
                ENLARGE_DURATION
        );

        return attackSkill;
    }

}
