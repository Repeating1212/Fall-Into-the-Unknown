package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_Sample.Behaviour.Tackle;

public class RatConfig {
    public static final int HEIGHT = 30;
    public static final int WIDTH = 45;
    public static final int SPEED = 4;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 1;

    public static final double DAMAGE = 2;
    public static final double ATTACK_RIGID = 2.5;
    public static final double DEAD_DURATION = 1.0;


    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(RatConfig.INITIAL_X, RatConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(RatConfig.WIDTH, RatConfig.HEIGHT, position, RatConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(RatConfig.SPEED, status);
        Health health = new Health(RatConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Tackle getTackle(Property owner){
        return new Tackle(RatConfig.DAMAGE, RatConfig.ATTACK_RIGID, owner);
    }
}
