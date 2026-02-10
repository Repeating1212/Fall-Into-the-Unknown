package BaseLevel.Objects.Class_Base;

import BaseLevel.Properties.Property;
import BaseLevel.Properties.Position;

import javafx.scene.Node;
import Lvl_Sample.Managers.Observer;

public abstract class GameObject implements DisplayableObject {

    protected Property property;
    protected final Observer observer;

    public static final double MapX = 0;
    public static final double MapY = 0;
    public static final double MapHeight = 1200;
    public static final double MapWidth = 675;

    public GameObject(Property property, Observer observer){
        this.property = property;
        this.observer = observer;
    }

    // Base Method

    public void update(double deltaTime){
        property.updateMovement(deltaTime);
        property.move(observer.getObjectProperties());
        checkBoundaries(MapX, MapY , MapHeight, MapWidth);
    }

    protected void checkBoundaries(double minX, double minY, double maxX, double maxY) {
        property.handleTouchBoundary(minX, minY, maxX, maxY);
    }

    // Data Class Getter

    public Property getProperty(){return property;}

    // Position related

    public double getX() { return property.getX(); }

    public double getY() { return property.getY(); }

    // Hitbox related

    public int getWidth() { return property.getWidth(); }

    public int getHeight() { return property.getHeight(); }

    public double getCenterX() { return property.getCenterX(); }

    public double getCenterY() { return property.getCenterY(); }

    public Position getCenterPos() {return property.getCenterPos();}

    public void setPosition(double x, double y) {
        property.setPosition(x, y);
    }

    // Movement control methods

    public void setMovement(double movementX, double movementY){
        property.setMovement(movementX, movementY);
    }

    public void pointTo(Position destination){
        property.pointTo(destination);
    }

    // Health Related

    public boolean takeDamage(double damage) {
        property.reduceHealth(damage);
        updateHealth();
        return true;
    }

    public int getHealth() {
        return property.getCurrentHealth();
    }

    public boolean isDead(){ return property.isDead();}

    public boolean isAlive() {return property.isAlive();}

    public boolean isHealthNull() {return property.isHealthNull();}

    // Override by subclass

    protected void updateHealth(){
        if (isHealthNull()) return;
        // Override in Subclass
    };

    public abstract Node getSprite();
}
