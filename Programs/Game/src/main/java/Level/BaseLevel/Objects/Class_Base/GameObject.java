package Level.BaseLevel.Objects.Class_Base;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Properties.Position;

import javafx.scene.Node;
import Level.BaseLevel.Manager.Observer;

import java.util.ArrayList;
import java.util.Objects;

public abstract class GameObject implements DisplayableObject {

    protected Property property;
    protected ArrayData<DisplayableObject> relatedDisplay = new ArrayData<>();
    protected boolean toRemove = false; // To remove from screen and updates

    public static final double MapX = 0;
    public static final double MapY = 0;
    public static final double MapHeight = 1200;
    public static final double MapWidth = 675;
    private static final int LAYER = 0;

    public GameObject(Property property){
        this.property = property;
        relatedDisplay.add(this);
    }

    // Base Method

    public void update(double deltaTime, Observer observer){
        if (property.isAlive()){
            updateAlive(deltaTime, observer);
        }

        toRemove = removeCondition();
        relatedDisplay = reloadDisplay();
        property.handleTouchBoundary(MapX, MapY , MapHeight, MapWidth);
        property.resolveCollision(observer.getGameObjPrt());
    }

    // Allow Override

    protected boolean removeCondition(){
        return (!property.isHealthNull() && property.isDead());
    }

    protected void updateAlive(double deltaTime, Observer observer){
        property.updateStatus(deltaTime);
        property.move(observer.getGameObjPrt());
    }

    protected ArrayData<DisplayableObject> reloadDisplay(){
        ArrayData<DisplayableObject> returnArray = new ArrayData<>();
        returnArray.add(this);
        return returnArray;
    }

    // Override DisplayableObject Method

    @Override
    public abstract Node getSprite();

    @Override
    public int getLayer(){
        return LAYER;
    }

    // Getter

    public Property getProperty(){return property;}

    public boolean toRemove(){return toRemove;}

    public ArrayList<DisplayableObject> getRelatedSprite(){
        return relatedDisplay.get();
    }

    // Position related

    public double getX() { return property.getX(); }

    public double getY() { return property.getY(); }

    public Position duplicatePosition(){return property.duplicatePosition();}

    public void resolveCollision(ArrayList<Property> properties) {
        property.resolveCollision(properties);
    }

    // Hitbox related

    public int getWidth() { return property.getWidth(); }

    public int getHeight() { return property.getHeight(); }

    public double getCenterX() { return property.getCenterX(); }

    public double getCenterY() { return property.getCenterY(); }

    public Position getCenterPos() {return property.getCenterPos();}

    public void setPosition(Position position) {
        property.setPosition(position.getX(), position.getY());
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

    public double getHealthPercentage(){
        return property.getHealthPercentage();
    }
}
