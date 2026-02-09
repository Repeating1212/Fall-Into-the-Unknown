package Level.Data.Suppliers;

import Level.Behaviour.MeleeAttack;
import Level.Data.Config.RatConfig;
import Level.Data.Properties.*;

public class RatSupplier {
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
