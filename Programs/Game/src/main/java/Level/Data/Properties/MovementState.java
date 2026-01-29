package Level.Data.Properties;

public class MovementState {
    // Movement states
    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private Direction currentDirection = Direction.NONE;
    private double speed;
    private double pauseMovementTimer = 0;

    public MovementState (double speed){
        this.speed = speed;
    }

    protected double getSpeed(){ return speed;}

    // Movement control methods
    protected void moveUp(boolean isMoving) {
        movingUp = isMoving;
    }

    protected void moveDown(boolean isMoving) {
        movingDown = isMoving;
    }

    protected void moveLeft(boolean isMoving) {
        movingLeft = isMoving;
    }

    protected void moveRight(boolean isMoving) {
        movingRight = isMoving;
    }

    protected double[] calculateMovement() {
        if (pauseMovementTimer > 0){
            return new double[] {0, 0};
        }
        updateDirection();
        double deltaX = currentDirection.getXMultiplier() * speed;
        double deltaY = currentDirection.getYMultiplier() * speed;
        return new double[] {deltaX, deltaY};
    }

    protected void updateDirection(){
        currentDirection = Direction.fromKeys(movingUp, movingDown, movingLeft, movingRight);
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
}
