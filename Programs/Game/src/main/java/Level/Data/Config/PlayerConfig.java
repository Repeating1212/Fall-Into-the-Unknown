package Level.Data.Config;

import javafx.scene.image.Image;

public class PlayerConfig {
    public static final int HEIGHT = 50;
    public static final int WIDTH = HEIGHT / 2;
    public static final int SPEED = 5;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 500;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 8;

    // Attack & Damage
    public static final double INITIAL_DAMAGE = 20;
    public static final double INITIAL_ATTACK_RANGE = 100;
    public static final double INITIAL_ATTACK_COOLDOWN = 1.0;
    public static final double ATTACK_AREA_ANGLE = 90;

    // Dash
    public static final double DASH_RANGE = 300;
    public static final double DASH_COOLDOWN = 3;

    // Defend
    public static final double DEFEND_DURATION = 1.5;
    public static final double DEFEND_COOLDOWN = 5.0;

    // Attack Animation
    public static final double ENLARGE_DURATION = 0.2;
    public static final double FADE_OUT_DURATION = 0.1;
    public static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 2.0;
}
