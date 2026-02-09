package Level.Data.Properties;

public class MovementState {
    // Movement states
    private double speed;
    private double pauseMovementTimer = 0;

    // New private value
    private double deltaX = 0;
    private double deltaY = 0;

    public MovementState (double speed){
        this.speed = speed;
    }

    protected double getSpeed(){ return speed;}

    protected boolean isMovingLeft() {return deltaX < 0;}
    protected boolean isMovingRight() {return deltaX > 0;}

    // Movement control methods

    protected double[] calculateMovement() {
        if (pauseMovementTimer > 0){
            return new double[] {0, 0};
        }
        double movementX = deltaX * speed;
        double movementY = deltaY * speed;
        return new double[] {movementX, movementY};
    }

    protected void setSpeed(double speed) {
        this.speed = speed;
    }

    protected void pauseMovement (double deltaTime){
        pauseMovementTimer = deltaTime;
    }

    protected void update(double deltaTime){
        if(pauseMovementTimer > 0){
            pauseMovementTimer -= deltaTime;
        }
    }

    protected void pointTo(Position destination, Position self){
        Vector2D vector = Vector2D.getAttackVector(self, destination);
        vector = vector.normalize();
        deltaX = vector.getX();
        deltaY = vector.getY();
    }

    protected void setMovement(double movementX, double movementY){
        this.deltaX = movementX;
        this.deltaY = movementY;
    }
}
