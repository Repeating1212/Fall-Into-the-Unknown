package Level.Lvl_2.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_1.Behaviour.Tackle;

public class SkeletonConfig {
    public static final int HEIGHT = 30;
    public static final int WIDTH = 45;
    public static final double SPEED = 150;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 5;
    public static final double TACKLE_DAMAGE = 1;
    public static final double TACKLE_RIGID = 2.5;

    public static final double DEAD_DURATION = 1.0;


    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(SkeletonConfig.INITIAL_X, SkeletonConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(SkeletonConfig.WIDTH, SkeletonConfig.HEIGHT, position, SkeletonConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SkeletonConfig.SPEED, status);
        Health health = new Health(SkeletonConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Tackle getTackle(Property owner){
        return new Tackle(SkeletonConfig.TACKLE_DAMAGE, SkeletonConfig.TACKLE_RIGID, owner);
    }
}
