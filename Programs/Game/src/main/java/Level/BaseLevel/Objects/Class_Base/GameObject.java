package Level.BaseLevel.Objects.Class_Base;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Properties.Position;

import javafx.scene.Node;
import Level.Lvl_Sample.Managers.Observer;

import java.util.ArrayList;

public abstract class GameObject implements DisplayableObject {

    protected Property property;
    protected ArrayData<DisplayableObject> relatedDisplay = new ArrayData<>();

    public static final double MapX = 0;
    public static final double MapY = 0;
    public static final double MapHeight = 1200;
    public static final double MapWidth = 675;

    public GameObject(Property property){
        this.property = property;
        relatedDisplay.add(this);
    }

    // Base Method

    public void update(double deltaTime, Observer observer){
        property.updateMovement(deltaTime);
        property.move(observer.getGameObjPrt());
        checkBoundaries(MapX, MapY , MapHeight, MapWidth);
    }

    protected void checkBoundaries(double minX, double minY, double maxX, double maxY) {
        property.handleTouchBoundary(minX, minY, maxX, maxY);
    }

    public ArrayList<DisplayableObject> getRelatedSprite(){
        return relatedDisplay.get();
    };

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
        return true;
    }

    public int getHealth() {
        return property.getCurrentHealth();
    }

    public boolean isDead(){ return property.isDead();}

    public boolean isAlive() {return property.isAlive();}

    public boolean isHealthNull() {return property.isHealthNull();}

    // Override by subclass

    public double getHealthPercentage(){
        return property.getHealthPercentage();
    }

    public abstract Node getSprite();
}
