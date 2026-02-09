package Lvl_Sample.Data.Suppliers;

import Lvl_Sample.Data.Config.PlayerConfig;
import Lvl_Sample.Data.Properties.*;

public class PlayerSupplier {

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
