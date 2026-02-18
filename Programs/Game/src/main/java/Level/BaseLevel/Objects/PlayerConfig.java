package Level.BaseLevel.Objects;

import Level.BaseLevel.Properties.*;

public class PlayerConfig {
    public static final int HEIGHT = 50;
    public static final int WIDTH = HEIGHT / 2;
    public static final int SPEED = 300;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 350;
    public static final boolean IS_BLOCKABLE = true;

    public static final double INVINCIBILITY_PERIOD = 2.5;
    public static final double DEAD_DURATION = 2.0;

    public static final int MAXIMUM_HEALTH = 8;

    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(PlayerConfig.INITIAL_X, PlayerConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(PlayerConfig.WIDTH, PlayerConfig.HEIGHT, position, PlayerConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(PlayerConfig.SPEED, status);
        Health health = new Health(PlayerConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }
}
