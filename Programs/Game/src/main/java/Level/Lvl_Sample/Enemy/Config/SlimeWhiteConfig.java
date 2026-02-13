package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_Sample.Behaviour.Rush;
import Level.Lvl_Sample.Behaviour.Tackle;

public class SlimeWhiteConfig {
    public static final int HEIGHT_Mul = 10;
    public static final int WIDTH_Mul = 15;
    public static final int SPEED = 2;
    public static final int INITIAL_X = 0;
    public static final int INITIAL_Y = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 10;

    public static final double DAMAGE = 2;
    public static final double ATTACK_RIGID = 2.5;
    public static final double DEAD_DURATION = 1.5;

    // Rush
    public static final double RUSH_COOLDOWN = 5;
    public static final double RUSH_RANGE = 300;
    public static final double RUSH_SPEED_MULTIPLY = 3.0;

    // Supplier

    public static Property getProperty(int size){
        return getProperty(size, new Position(INITIAL_X, INITIAL_Y));
    }

    public static Property getProperty(int size, Position position){
        int width = (1 + size) * WIDTH_Mul;
        int height = (1 + size) * HEIGHT_Mul;
        HitBox hitBox = new HitBox(width, height, position, SlimeWhiteConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(SlimeWhiteConfig.SPEED);
        Health guardHeart = new Health(SlimeWhiteConfig.MAXIMUM_HEALTH);
        return new Property(hitBox, movementState, guardHeart);
    }

    public static Health getHealth(){
        return new Health(SlimeWhiteConfig.MAXIMUM_HEALTH);
    }

    public static Tackle getTackle(int size){
        return new Tackle(size, SlimeWhiteConfig.ATTACK_RIGID);
    }

    public static Rush getRush(Property owner){
        return new Rush(RUSH_RANGE, RUSH_COOLDOWN, RUSH_SPEED_MULTIPLY, owner);
    }
}
