package Level.BaseLevel.Properties;

import Data.DataClass.Vector2D;

public class MovementState {

    private final Status status;
    // Movement states
    private final double SPEED;
    private double deltaX = 0;
    private double deltaY = 0;
    private double speedMultiply = 1;

    public MovementState (double SPEED, Status status){
        this.SPEED = SPEED;
        this.status = status;
    }

    protected double getSpeed(){ return SPEED * speedMultiply;}

    protected boolean isMovingLeft() {return deltaX < 0;}
    protected boolean isMovingRight() {return deltaX > 0;}

    // Movement control methods

    protected double[] calculateMovement() {
        double movementX = deltaX * SPEED * speedMultiply;
        double movementY = deltaY * SPEED * speedMultiply;
        return new double[] {movementX, movementY};
    }

    protected void speedMultiply(double speedPercentage) {
        this.speedMultiply *= speedPercentage;
    }

    protected void speedDivide(double speedPercentage) {
        this.speedMultiply /= speedPercentage;
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
