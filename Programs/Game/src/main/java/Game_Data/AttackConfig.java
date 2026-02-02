package Game_Data;

import Level.Skills.Attacks.AttackArea;
import Level.Skills.Attacks.AttackSkill;
import Level.Skills.Attacks.ConeAttackArea;
import Level.View.AttackVisualize.ConeAttackVisual;
import LoadFile.DataManager;
import LoadFile.SkillFile.AttackFile;

public class AttackConfig {

    private static final int TOTAL_UPGRADE = 4;

    // AttackConfig Animation
    private static final double ENLARGE_DURATION = 0.2;
    private static final double FADE_OUT_DURATION = 0.1;
    private static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    private static final double RIGID_TIME = TOTAL_ANIMATION_PERIOD * 2.0;

    // Upgrades
    private static final double DAMAGE_l1 = 6;
    private static final double DAMAGE_l2 = 12;
    private static final double DAMAGE_l3 = 18;

    private static final double RANGE_l1 = 100;
    private static final double RANGE_l2 = 120;
    private static final double RANGE_l3 = 140;

    private static final double COOLDOWN_l1 = 2.0;
    private static final double COOLDOWN_l2 = 1.5;
    private static final double COOLDOWN_l3 = 1.0;

    private static final double AREA_ANGLE_l1 = 60;
    private static final double AREA_ANGLE_l2 = 75;
    private static final double AREA_ANGLE_l3 = 90;

    public static final double[] DAMAGE_UPG = new double[]{
            DAMAGE_l1, DAMAGE_l2, DAMAGE_l3
    };
    public static final double[] RANGE_UPG = new double[]{
            RANGE_l1, RANGE_l2, RANGE_l3
    };
    public static final double[] COOLDOWN_UPG = new double[]{
            COOLDOWN_l1,  COOLDOWN_l2, COOLDOWN_l3
    };
    public static final double[] AREA_ANGLE_UPG = new double[]{
            AREA_ANGLE_l1, AREA_ANGLE_l2, AREA_ANGLE_l3
    };


    public static AttackSkill getAttack(){
        AttackFile attackFile = DataManager.getAttackFile();
        double damage = DAMAGE_UPG[attackFile.getDamageUpgrade()];
        double cooldown = COOLDOWN_UPG[attackFile.getCooldownUpgrade()];
        double range = RANGE_UPG[attackFile.getRangeUpgrade()];
        double areaAngle = AREA_ANGLE_UPG[attackFile.getAreaAngleUpgrade()];

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
