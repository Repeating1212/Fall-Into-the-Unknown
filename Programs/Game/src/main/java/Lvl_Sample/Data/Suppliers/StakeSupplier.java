package Lvl_Sample.Data.Suppliers;

import Lvl_Sample.Data.Config.StakeConfig;
import BaseLevel.Properties.*;

public class StakeSupplier {
    public static Property getProperty(){
        Position position =  new Position(StakeConfig.INITIAL_X, StakeConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(StakeConfig.WIDTH, StakeConfig.HEIGHT, position, StakeConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(StakeConfig.SPEED);
        Health health = new Health(StakeConfig.INITIAL_HEALTH);
        return new Property(hitBox, movementState, health);
    }

    public static Health getHealth(){
        return new Health(StakeConfig.INITIAL_HEALTH);
    }
}
