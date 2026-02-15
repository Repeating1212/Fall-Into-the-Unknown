package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_Sample.Behaviour.Tackle;

public class SlimeGreyConfig {
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

    public static final int MIN_SPAWN = 3;
    public static final int MAX_SPAWN = 4;


    // Supplier

    public static Property getProperty(int size){
        return getProperty(size, new Position(INITIAL_X, INITIAL_Y));
    }

    public static Property getProperty(int size, Position position){
        Status status = new Status();
        int width = (1 + size) * WIDTH_Mul;
        int height = (1 + size) * HEIGHT_Mul;
        HitBox hitBox = new HitBox(width, height, position, SlimeGreyConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SlimeGreyConfig.SPEED,  status);
        Health health = new Health(SlimeGreyConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Tackle getTackle(int size, Property owner){
        return new Tackle(size, SlimeGreyConfig.ATTACK_RIGID, owner);
    }
}
