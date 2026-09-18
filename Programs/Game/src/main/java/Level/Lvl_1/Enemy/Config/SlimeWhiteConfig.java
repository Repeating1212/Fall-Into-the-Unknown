package Level.Lvl_1.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_1.Behaviour.Rush;
import Level.Lvl_1.Behaviour.Tackle;

public class SlimeWhiteConfig {
    public static final int HEIGHT_Mul = 12;
    public static final int WIDTH_Mul = 18;
    public static final int SPEED = 100;
    public static final int INITIAL_X = 0;
    public static final int INITIAL_Y = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final int HEALTH_Mul = 6;

    public static final double DAMAGE = 2;
    public static final double ATTACK_RIGID = 2.5;
    public static final double DEAD_DURATION = 1.5;

    // Rush
    public static final double RUSH_COOLDOWN = 5;
    public static final double RUSH_RANGE_Mul = 75;
    public static final double RUSH_SPEED_MULTIPLY = 3;
    public static final double RUSH_RIGID_Mul = 0.5;

    // Supplier

    public static Property getProperty(int size){
        return getProperty(size, new Position(INITIAL_X, INITIAL_Y));
    }

    public static Property getProperty(int size, Position position){
        Status status = new Status();
        int width = (1 + size) * WIDTH_Mul;
        int height = (1 + size) * HEIGHT_Mul;
        int healthNum = (1 + size) * HEALTH_Mul;
        HitBox hitBox = new HitBox(width, height, position, SlimeWhiteConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SlimeWhiteConfig.SPEED, status);
        Health health = new Health(healthNum, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Rush getRush(Property owner, int size){
        return new Rush(RUSH_RANGE_Mul * (1 + size), RUSH_COOLDOWN, DAMAGE, RUSH_SPEED_MULTIPLY, owner, RUSH_RIGID_Mul * (1 + size));
    }

    public static Tackle getTackle(Property owner, int size){
        return new Tackle(size, ATTACK_RIGID, owner);
    }
}
