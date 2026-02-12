package Level.BaseLevel.Objects;

import Level.BaseLevel.Properties.*;

public class PlayerConfig {
    public static final int HEIGHT = 50;
    public static final int WIDTH = HEIGHT / 2;
    public static final int SPEED = 5;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 500;
    public static final boolean IS_BLOCKABLE = true;

    public static final double INVINCIBILITY_PERIOD = 2.5;

    public static final int MAXIMUM_HEALTH = 8;

    // Supplier

    public static Property getProperty(){
        Position playerPosition =  new Position(PlayerConfig.INITIAL_X, PlayerConfig.INITIAL_Y);
        HitBox playerHitBox = new HitBox(PlayerConfig.WIDTH, PlayerConfig.HEIGHT, playerPosition, PlayerConfig.IS_BLOCKABLE);
        MovementState playerMovement = new MovementState(PlayerConfig.SPEED);
        Health playerHealth = new Health(PlayerConfig.MAXIMUM_HEALTH);
        return new Property(playerHitBox, playerMovement, playerHealth);
    }

    public static Health getHealth(){
        return new Health(PlayerConfig.MAXIMUM_HEALTH);
    }
}
