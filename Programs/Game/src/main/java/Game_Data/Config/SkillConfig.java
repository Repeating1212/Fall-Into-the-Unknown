package Game_Data.Config;

import Level.Data.Suppliers.PlayerSupplier;
import Level.Skills.Skill;

public class SkillConfig {

    public static final int EMPTY_SKILL_ID = -1;
    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

    // Attack
    public static final double ATTACK_DAMAGE = 20;
    public static final double ATTACK_RANGE = 100;
    public static final double ATTACK_COOLDOWN = 1.0;
    public static final double ATTACK_AREA_ANGLE = 90;

    // Attack Animation
    public static final double ATTACK_ENLARGE_DURATION = 0.2;
    public static final double ATTACK_FADE_OUT_DURATION = 0.1;
    public static final double TOTAL_ANIMATION_PERIOD = ATTACK_FADE_OUT_DURATION + ATTACK_ENLARGE_DURATION;
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 2.0;

    // Dash
    public static final double DASH_RANGE = 300;
    public static final double DASH_COOLDOWN = 3;

    // Defend
    public static final double DEFEND_DURATION = 1.5;
    public static final double DEFEND_COOLDOWN = 5.0;
}
