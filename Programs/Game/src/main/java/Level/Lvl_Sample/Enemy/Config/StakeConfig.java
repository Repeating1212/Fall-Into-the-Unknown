package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Properties.*;

public class StakeConfig {

    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int SPEED = 0;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int INITIAL_HEALTH = 20;
    public static final int DEAD_DURATION = 20;


    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(StakeConfig.INITIAL_X, StakeConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(StakeConfig.WIDTH, StakeConfig.HEIGHT, position, StakeConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(StakeConfig.SPEED, status);
        Health health = new Health(StakeConfig.INITIAL_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

}
