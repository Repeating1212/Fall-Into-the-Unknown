package Level.BaseLevel.Objects.Config;

import Level.BaseLevel.Properties.*;

public class PortalConfig {
    public static final int HEIGHT = 50;
    public static final int WIDTH = 50;
    public static final int SPEED = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 1;

    // Supplier

    public static Property getProperty(Property property){
        Position playerPosition = property.duplicatePosition();
        HitBox hitBox = new HitBox(PortalConfig.WIDTH, PortalConfig.HEIGHT, playerPosition, PortalConfig.IS_BLOCKABLE);
        MovementState movementState = new MovementState(PortalConfig.SPEED);
        Health health = new Health(PortalConfig.MAXIMUM_HEALTH);
        return new Property(hitBox, movementState, health);
    }

    public static Health getHealth(){
        return new Health(PortalConfig.MAXIMUM_HEALTH);
    }
}
