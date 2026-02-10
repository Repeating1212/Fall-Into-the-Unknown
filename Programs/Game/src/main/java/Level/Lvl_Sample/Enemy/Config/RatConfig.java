package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_Sample.Behaviour.MeleeAttack;

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

    // Supplier

    public static Property getProperty(){
        Position guardPosition =  new Position(RatConfig.INITIAL_X, RatConfig.INITIAL_Y);
        HitBox guardHitBox = new HitBox(RatConfig.WIDTH, RatConfig.HEIGHT, guardPosition, RatConfig.IS_BLOCKABLE);
        MovementState guardMovementState = new MovementState(RatConfig.SPEED);
        Health guardHeart = new Health(RatConfig.MAXIMUM_HEALTH);
        return new Property(guardHitBox, guardMovementState, guardHeart);
    }

    public static Health getHealth(){
        return new Health(RatConfig.MAXIMUM_HEALTH);
    }

    public static MeleeAttack getMeleeAttack(){
        return new MeleeAttack(RatConfig.DAMAGE, RatConfig.ATTACK_RIGID);
    }
}
