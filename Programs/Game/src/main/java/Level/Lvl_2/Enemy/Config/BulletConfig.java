package Level.Lvl_2.Enemy.Config;

import Level.BaseLevel.Properties.*;

public class BulletConfig {
    public static final int HEIGHT = 15;
    public static final int WIDTH = 15;
    public static final double SPEED = 300;
    public static final int INITIAL_X = 0;
    public static final int INITIAL_Y = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final double DEAD_DURATION = 0.0;

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(BulletConfig.INITIAL_X, BulletConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(BulletConfig.WIDTH, BulletConfig.HEIGHT, position, SkeletonConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SkeletonConfig.SPEED, status);
        Health health = null;
        return new Property(hitBox, movementState, health, status);
    }
}
