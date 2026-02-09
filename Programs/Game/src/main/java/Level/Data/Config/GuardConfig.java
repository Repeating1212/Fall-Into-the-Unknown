package Level.Data.Config;

public class GuardConfig {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int SPEED = 4;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 20;

    // AttackConfig Component
    public static final double INITIAL_DAMAGE = 8;
    public static final double INITIAL_ATTACK_RANGE = 200;
    public static final double ATTACK_AREA_ANGLE = 135;
    // AttackConfig Behaviour
    public static final double ATTACK_DISTANCE_OFFSET = INITIAL_ATTACK_RANGE * 0.8;
    // AttackConfig Animation
    public static final double FADE_OUT_DURATION = 0.3;
    public static final double ENLARGE_DURATION = 1.5;
    public static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    // AttackConfig time-related
    public static final double INITIAL_ATTACK_COOLDOWN = 3.0;
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 1.2;
}
