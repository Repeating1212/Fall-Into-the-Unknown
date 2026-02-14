package Data.Config;

import Data.Loader.ImageLoader;
import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Level.BaseLevel.Skills.Attack;
import Level.BaseLevel.Skills.ConeAttackArea;
import Level.BaseLevel.View.AttackVisualize.ConeAttackVisual;
import LoadFile.FileManager;
import LoadFile.SkillFile.AttackFile;

public class AttackConfig extends SkillConfig {

    public static final int DAMAGE_ID = 1;
    public static final int RANGE_ID = 2;
    public static final int COOLDOWN_ID = 3;
    public static final int ATTACK_ANGLE_ID = 4;

    public AttackConfig(){

        super(  ImageLoader.ATTACK_ICON,
                ImageLoader.ATTACK_ICON,
                AttackFile.class,
                "Attack"
        );

        this.upgradeValues = new UpgradeValue[]{
                damageUpgValue, rangeUpgValue, cooldownUpgValue, angleUpgValue
        };
    }

    @Override
    public void setConfigID(int configID){
        super.setConfigID(configID);
    }

    // AttackConfig Animation
    private static final double ENLARGE_DURATION = 0.2;
    private static final double FADE_OUT_DURATION = 0.1;
    private static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    private static final double RIGID_TIME = TOTAL_ANIMATION_PERIOD + 0.2;

    private final UpgradeValue damageUpgValue = new UpgradeValue(
            new double[]{ 6, 12, 18},
            new double[]{ 2, 4, 4},
            DAMAGE_ID
    );

    private final UpgradeValue rangeUpgValue = new UpgradeValue(
            new double[]{ 100, 120, 140},
            new double[]{ 4, 4, 4},
            RANGE_ID
    );

    private final UpgradeValue cooldownUpgValue = new UpgradeValue(
            new double[]{ 2.0, 1.5, 1.0},
            new double[]{ 4, 8, 8},
            COOLDOWN_ID
    );

    private final UpgradeValue angleUpgValue = new UpgradeValue(
            new double[]{ 60, 75, 90},
            new double[]{ 4, 8, 8},
            ATTACK_ANGLE_ID
    );

    private final UpgradeText damageUpg = new UpgradeText(
            "Attack", "Increase Damage", "Damage",
            "", ImageLoader.ATTACK_ICON, damageUpgValue
    );

    private final UpgradeText rangeUpg = new UpgradeText(
            "Attack", "Increase Range", "Range",
            "", ImageLoader.ATTACK_ICON, rangeUpgValue
    );

    private final UpgradeText cooldownUpg = new UpgradeText(
            "Attack", "Decrease Cooldown", "Cooldown",
            "s", ImageLoader.ATTACK_ICON, cooldownUpgValue
    );

    private final UpgradeText angleUpg = new UpgradeText(
            "Attack", "Increase Attack Angle", "Attack Angle",
            "\u00B0", ImageLoader.ATTACK_ICON, angleUpgValue
    );

    // Public Method

    private final UpgradeText[] upgradeTexts = new UpgradeText[]{
            damageUpg, rangeUpg, cooldownUpg, angleUpg
    };

    public final UpgradeText[] getUpgradeTexts(){
        return upgradeTexts;
    }


    public Attack getSkill(FileManager fileManager){
        AttackFile attackFile = fileManager.getAttackFile();

        double damage = damageUpgValue.getValue(attackFile.getDamageUpgrade());
        double cooldown = cooldownUpgValue.getValue(attackFile.getCooldownUpgrade());
        double range = rangeUpgValue.getValue(attackFile.getRangeUpgrade());
        double areaAngle = angleUpgValue.getValue(attackFile.getAreaAngleUpgrade());

        AttackArea attackArea = new ConeAttackArea(
                range, areaAngle
        );

        ConeAttackVisual attackVisual = new ConeAttackVisual(
                range, areaAngle, ENLARGE_DURATION, FADE_OUT_DURATION
        );

        Attack attackSkill = new Attack(
                attackVisual,
                AttackConfig.RIGID_TIME,
                attackArea,
                damage,
                cooldown,
                ENLARGE_DURATION,
                TOTAL_ANIMATION_PERIOD
        );

        return attackSkill;
    }

}
