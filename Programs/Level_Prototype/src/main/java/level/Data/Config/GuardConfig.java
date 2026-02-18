package level.Data.Config;

import javafx.scene.image.Image;

public class GuardConfig {
    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int SPEED = 4;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 20;

    // Attack Component
    public static final double INITIAL_DAMAGE = 8;
    public static final double INITIAL_ATTACK_RANGE = 200;
    public static final double ATTACK_AREA_ANGLE = 135;
    // Attack Behaviour
    public static final double ATTACK_DISTANCE_OFFSET = INITIAL_ATTACK_RANGE * 0.8;
    // Attack Animation
    public static final double FADE_OUT_DURATION = 0.3;
    public static final double ENLARGE_DURATION = 1.5;
    public static final double TOTAL_ANIMATION_PERIOD = FADE_OUT_DURATION + ENLARGE_DURATION;
    // Attack time-related
    public static final double INITIAL_ATTACK_COOLDOWN = 3.0;
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 1.2;

    public static Image loadImage() {
        Image image = new Image(PlayerConfig.class.getResourceAsStream("/picture/Stake.png"));
        if (image.isError()) {
            System.out.println("Coin image not found, using placeholder");
        }
        return image;
    }
}
