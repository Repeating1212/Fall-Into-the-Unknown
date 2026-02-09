package Lvl_Sample.Data.Suppliers;

import Lvl_Sample.Data.Config.CoinConfig;
import Lvl_Sample.Data.Properties.*;

public class CoinSupplier {
    public static Property getProperty(Property property){
        Position position = property.duplicatePosition();
        HitBox hitBox = new HitBox(CoinConfig.WIDTH, CoinConfig.HEIGHT, position, CoinConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(CoinConfig.SPEED);
        Health health = null;
        return new Property(hitBox, movementState, health);
    }
}
