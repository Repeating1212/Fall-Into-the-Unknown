package level.Skills.Player;

import level.Skills.Timer;
import level.Data.Properties.Property;
import level.Data.Properties.HitBox;
import level.Data.Properties.Position;

import java.util.ArrayList;

public class Dash {

    private final double RANGE;

    private final Timer cooldownSystem;
    private boolean pendingDash;
    private Position destination = new Position();

    public Dash (Double cooldown, Double range){
        this.cooldownSystem = new Timer(cooldown);
        this.RANGE = range;
    }

    public double getCooldownPercentage(){
        return cooldownSystem.getCooldownPercentage();
    }

    public void setDash(double positionX, double positionY){
        if (cooldownSystem.isDeactive()){
            destination.setX(positionX);
            destination.setY(positionY);
            pendingDash = true;
        }
    }

    public void updateDash(Property property, double deltaTime , ArrayList<Property> objects){
        cooldownSystem.update(deltaTime);
        if (pendingDash){
            Position limitedPos = limitDashRange(property, destination);
            property.move(limitedPos, objects);
            cooldownSystem.start();
            pendingDash = false;
        }
    }

    // Private Method

    private Position limitDashRange(Property property, Position destination) {
        double dx = destination.getX() - property.getCenterX();
        double dy = destination.getY() - property.getCenterY();
        double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance <= RANGE) {
            return new Position(dx, dy);
        }

        // Normalize to max range
        double ratio = RANGE / distance;
        return new Position(dx * ratio, dy * ratio);
    }
}
