package Lvl_Sample.Data.Suppliers;

import Lvl_Sample.Data.Config.PortalConfig;
import Lvl_Sample.Data.Properties.*;

public class PortalSupplier {
    public static Property getProperty(){
        Position playerPosition =  new Position();
        HitBox hitBox = new HitBox(PortalConfig.WIDTH, PortalConfig.HEIGHT, playerPosition, PortalConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(PortalConfig.SPEED);
        Health health = new Health(PortalConfig.MAXIMUM_HEALTH);
        return new Property(hitBox, movementState, health);
    }

    public static Health getHealth(){
        return new Health(PortalConfig.MAXIMUM_HEALTH);
    }
}

