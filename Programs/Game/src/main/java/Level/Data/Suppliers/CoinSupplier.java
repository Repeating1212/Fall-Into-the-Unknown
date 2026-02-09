package Level.Data.Suppliers;

import Level.Data.Config.CoinConfig;
import Level.Data.Properties.*;

public class CoinSupplier {
    public static Property getProperty(Property property){
        Position position = property.duplicatePosition();
        HitBox hitBox = new HitBox(CoinConfig.WIDTH, CoinConfig.HEIGHT, position, CoinConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(CoinConfig.SPEED);
        Health health = null;
        return new Property(hitBox, movementState, health);
    }
}
