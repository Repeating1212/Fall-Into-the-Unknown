package Level.BaseLevel.Properties;

import Data.DataClass.Vector2D;

public class MovementState {

    private final Status status;
    // Movement states
    private double baseSpeed;
    private double deltaX = 0;
    private double deltaY = 0;

    public MovementState (double SPEED, Status status){
        this.baseSpeed = SPEED;
        this.status = status;
    }

    protected double getBaseSpeed(){ return baseSpeed;}

    protected void setBaseSpeed(int speed){
        baseSpeed = speed;
    }

    protected boolean isMovingLeft() {return deltaX < 0;}
    protected boolean isMovingRight() {return deltaX > 0;}

    // Movement control methods

    protected double[] calculateMovement(double deltaTime) {
        double multipliedMovementX = deltaX * baseSpeed * status.getSpeedMultiply();
        double multipliedMovementY = deltaY * baseSpeed * status.getSpeedMultiply();

        double increasedMovementX  = multipliedMovementX + (deltaX * status.getSpeedIncrement());
        double increasedMovementY  = multipliedMovementY + (deltaY * status.getSpeedIncrement());

        return new double[] {increasedMovementX * deltaTime, increasedMovementY * deltaTime};
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
