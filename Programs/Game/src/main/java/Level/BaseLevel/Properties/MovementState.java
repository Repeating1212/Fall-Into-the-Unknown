package Level.BaseLevel.Properties;

import Data.DataClass.Vector2D;

public class MovementState {
    // Movement states
    private final double SPEED;
    private double deltaX = 0;
    private double deltaY = 0;
    private double speedMultiply = 1;

    private double pauseMovementTimer = 0;
    private boolean pauseMovement = false;
    private boolean directable = true;



    public MovementState (double SPEED){
        this.SPEED = SPEED;
    }

    protected double getSpeed(){ return SPEED * speedMultiply;}

    protected boolean isMovingLeft() {return deltaX < 0;}
    protected boolean isMovingRight() {return deltaX > 0;}

    // Movement control methods

    protected double[] calculateMovement() {
        if (pauseMovementTimer > 0){
            return new double[] {0, 0};
        }
        if (pauseMovement){
            return new double[] {0, 0};
        }
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

    protected void pauseMovement (double deltaTime){
        pauseMovementTimer = deltaTime;
    }

    protected void setDirectable(boolean directable){
        this.directable = directable;
    }

    protected void update(double deltaTime){
        if(pauseMovementTimer > 0){
            pauseMovementTimer -= deltaTime;
        }
    }

    protected void pointTo(Position destination, Position self){
        if (directable) {
            Vector2D vector = Vector2D.getAttackVector(self, destination);
            vector = vector.normalize();
            deltaX = vector.getX();
            deltaY = vector.getY();
        }

    }

    protected void setMovement(double movementX, double movementY){
        if (directable){
            this.deltaX = movementX;
            this.deltaY = movementY;
        }
    }

    protected double getDirection(){
        Vector2D vector2D = new Vector2D(deltaX, deltaY);
        return vector2D.getAngleDegrees();
    }

    protected void setPauseMovement(boolean pauseMovement){
        this.pauseMovement = pauseMovement;
    }
}
