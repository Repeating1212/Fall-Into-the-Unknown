package Level.BaseLevel.Properties;

import Data.DataClass.Vector2D;

public class MovementState {

    private final Status status;
    // Movement states
    private final double SPEED_PER_SECOND;
    private double deltaX = 0;
    private double deltaY = 0;

    public MovementState (double SPEED, Status status){
        this.SPEED_PER_SECOND = SPEED;
        this.status = status;
    }

    protected double getSpeed(){ return SPEED_PER_SECOND;}

    protected boolean isMovingLeft() {return deltaX < 0;}
    protected boolean isMovingRight() {return deltaX > 0;}

    // Movement control methods

    protected double[] calculateMovement(double deltaTime) {
        double movementX = deltaX * SPEED_PER_SECOND * status.getSpeedMultiply() * deltaTime;
        double movementY = deltaY * SPEED_PER_SECOND * status.getSpeedMultiply() * deltaTime;
        return new double[] {movementX, movementY};
    }

    protected void pointTo(Position destination, Position self){
        if ( status.directable()) {
            Vector2D vector = Vector2D.getAttackVector(self, destination);
            vector = vector.normalize();
            deltaX = vector.getX();
            deltaY = vector.getY();
        }

    }

    protected void setMovement(double movementX, double movementY){
        if (status.directable()){
            this.deltaX = movementX;
            this.deltaY = movementY;
        }
    }

    protected double getDirection(){
        Vector2D vector2D = new Vector2D(deltaX, deltaY);
        return vector2D.getAngleDegrees();
    }

    protected Vector2D getVector(){
        return new Vector2D(deltaX, deltaY);
    }
}
