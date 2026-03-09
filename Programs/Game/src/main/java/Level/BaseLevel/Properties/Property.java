package Level.BaseLevel.Properties;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Property {
    private final Position position;
    private final HitBox hitBox;
    private final MovementState movementState;
    private final Health health;
    private final Status status;

    public Property(HitBox hitBox, MovementState movementState, Health health, Status status){
        this.hitBox = hitBox;
        this.position = hitBox.getPosition();
        this.movementState = movementState;
        this.health = health;
        this.status = status;
        this.position.setStatus(status);
    }

    // Status

    public void updateStatus(double deltaTime){
        status.update(deltaTime);
    }

    public void pauseMovement (Double duration){
        status.pauseMovement(duration);
    }

    public void pauseDirect (Double duration){
        status.pauseDirect(duration);
    }


    public boolean isInvincible(){ return status.isInvincible();}

    public void setInvincible(double duration){ status.setInvincible(duration);}

    public boolean isDefend() { return  status.isDefend();}

    public void setDefend(double duration){
        status.setDefend(duration);
    }

    public void setSpeedMultiply(double duration, double speedMultiply){
        status.setSpeedMultiply(duration, speedMultiply);
    }
    // HitBox

    public void resetDebugHitBox(){ hitBox.resetBugView();}

    public void showDebugHitBox(){
        hitBox.showDebug(true);
    }

    public Rectangle getDebugHitBox() {
        return hitBox.getDebugHitBox();
    }

    public boolean isCollide (Property other) {
        return hitBox.isCollide(other.hitBox);
    }

    public boolean isTouch(Property other) {return hitBox.isTouch(other.hitBox);}

    public boolean isCollide (ArrayList<Property> others) {
        ArrayList<HitBox> othersHitBox = getHitBoxArray(others);
        return hitBox.isCollide(othersHitBox);
    }

    public int getWidth() { return hitBox.getWidth(); }

    public int getHeight() { return hitBox.getHeight(); }

    public double getCenterX() { return hitBox.getCenterX(); }

    public double getCenterY() { return hitBox.getCenterY(); }

    public Position getCenterPos() {return hitBox.getCenterPos();}

    public Position[] getCorner(){
        Position[] corners = {
                hitBox.getTopLeft(),
                hitBox.getTopRight(),
                hitBox.getBottomLeft(),
                hitBox.getBottomRight()
        };
        return corners;
    }

    // MovementState

    public double getAverageSpeed() { return
            movementState.getSpeed();
    }

    public boolean isMovingLeft() {return movementState.isMovingLeft();}
    public boolean isMovingRight() {return movementState.isMovingRight();}

    public void setMovement(double movementX, double movementY){
        movementState.setMovement(movementX, movementY);
    }

    public double getDirection(){
        return movementState.getDirection();
    }

    public void pointTo(Property destination){
        movementState.pointTo(destination.position, this.position);
    }

    public void pointTo(Position destination){
        movementState.pointTo(destination, this.position);
    }

    // Position

    public double getX() { return position.getX(); }

    public double getY() { return position.getY(); }

    public void move(Position increment, ArrayList<Property> properties){
        position.move(increment.getX(), increment.getY(), this.hitBox, getHitBoxArray(properties));
    }

    public void move(ArrayList<Property> properties, double deltaTime){
        position.move(movementState.calculateMovement(deltaTime), this.hitBox, getHitBoxArray(properties));
    }

    public void move(double incrementX, double incrementY,  ArrayList<Property> properties){
        position.move(incrementX, incrementY, this.hitBox, getHitBoxArray(properties));
    }

    public void moveTo(Position destination, ArrayList<Property> properties){
        position.moveTo(destination, this.hitBox, getHitBoxArray(properties));
    }

    public void setPosition(double x, double y) {
        position.setX(x);
        position.setY(y);
    }

    public void handleTouchBoundary(double minX, double minY, double maxX, double maxY){
        position.handleTouchBoundary(this.hitBox, minX, minY, maxX, maxY);
    }

    public Position duplicatePosition(){
        return new Position(position.getX(), position.getY());
    }

    public void spawnNearBoundary(ArrayList<Property> properties){
        position.spawnNearBoundary(this.hitBox, getHitBoxArray(properties));
    }

    public void spawnNearBy(ArrayList<Property> others, Position destination){
        position.spawnNearBy(this.hitBox, getHitBoxArray(others), destination);
    }

    public void resolveCollision(ArrayList<Property> properties){
        position.resolveCollision(this.hitBox, getHitBoxArray(properties));
    }

    // Health

    public void reduceHealth(double damage) {
        if (health == null) return;
        health.reduceHealth(damage);
    }

    public void increaseHealth(double increment){
        if (health == null) return;
        health.increaseHealth(increment);
    }

    // Getter
    public boolean isHealthNull(){
        return (health == null);
    }

    public int getCurrentHealth() {
        if (health == null) return 0;
        return health.getHealth();
    }

    public double getHealthPercentage(){
        if (health == null) return 0;
        return health.getHealthPercentage();
    }

    public boolean isAlive(){
        if (health == null) return false;
        return health.isAlive();
    }

    public boolean isDead(){
        if (health == null) return false;
        return health.isDead();
    }

    // Private Method

    private ArrayList<HitBox> getHitBoxArray(ArrayList<Property> properties){
        if (properties == null) return null;
        ArrayList<HitBox> hitBoxes = new ArrayList<>();
        for (Property property : properties){
            hitBoxes.add(property.hitBox);
        }
        return hitBoxes;
    }
}
