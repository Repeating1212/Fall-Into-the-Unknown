package level.Data.Suppliers;

import level.Data.Config.PortalConfig;
import level.Data.Properties.*;

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

