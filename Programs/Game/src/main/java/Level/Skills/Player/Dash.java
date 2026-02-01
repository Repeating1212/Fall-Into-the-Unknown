package Level.Skills.Player;

import Level.Managers.Observer;
import Level.Skills.Skill;
import Level.Skills.Timer;
import Level.Data.Properties.Property;
import Level.Data.Properties.Position;

public class Dash implements Skill {

    private final double RANGE;
    private final Property owner;

    private final Timer cooldownSystem;
    private boolean pendingDash;
    private Position destination = new Position();

    public Dash (Double cooldown, Double range, Property owner){
        this.cooldownSystem = new Timer(cooldown);
        this.RANGE = range;
        this.owner = owner;
    }

    public double getCooldownPercentage(){
        return cooldownSystem.getCooldownPercentage();
    }

    public void activate(double positionX, double positionY){
        activate(new Position(positionX, positionY));
    }

    public void activate(Position position){
        if (cooldownSystem.isDeactive()){
            destination.setX(position.getX());
            destination.setY(position.getY());
            pendingDash = true;
        }
    }

    public void update( double deltaTime , Observer observer){
        cooldownSystem.update(deltaTime);
        if (pendingDash){
            Position limitedPos = limitDashRange(owner, destination);
            owner.move(limitedPos, observer.getObjectProperties());
            cooldownSystem.start();
            pendingDash = false;
        }
    }

    public void handleRigid(){
        // No skill rigid for dash
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
