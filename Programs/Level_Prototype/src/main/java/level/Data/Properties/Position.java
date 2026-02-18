package level.Data.Properties;

import java.util.ArrayList;

public class Position {
    private double x;
    private double y;

    public Position(double startX, double startY){
        this.x = startX;
        this.y = startY;
    }

    public Position(){
        this.x = 0;
        this.y = 0;
    }

    public void setX(double x) { this.x = x; }
    public void setY(double y) { this.y = y; }

    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }

    protected void handleTouchBoundary(HitBox hitBox, double minX, double minY, double maxX, double maxY){
        if (x < minX) x = minX;
        if (y < minY) y = minY;
        if (x > maxX - hitBox.getWidth()) x = maxX - hitBox.getWidth();
        if (y > maxY - hitBox.getHeight()) y = maxY - hitBox.getHeight();
    }

    protected void moveTo(Position destination, HitBox ownerHB , ArrayList<HitBox> objectHB){
        double movementX = destination.getX() - x;
        double movementY = destination.getY() - y;
        move(movementX,movementY, ownerHB, objectHB);
    }

    protected void move(double incrementX, double incrementY, HitBox ownerHB ,ArrayList<HitBox> objectsHB){
        double[] movement = new double[] {incrementX, incrementY};
        move(movement, ownerHB, objectsHB);
    }

    protected void move(double[] movement, HitBox ownerHB ,ArrayList<HitBox> objectsHB){
        double deltaX = (movement[0] > 0) ? 0.1 : -0.1;
        double deltaY = (movement[1] > 0) ? 0.1 : -0.1;
        int targetStepX = Math.abs((int) movement[0]); // Increment loop calculation
        int targetStepY = Math.abs((int) movement[1]);

        for(double step = 0; step < (Math.max(targetStepX, targetStepY)); step += 0.1){
            if (targetStepX > step){
                x += deltaX;
                if (ownerHB.isBlocked(objectsHB)) {
                    x -= deltaX;  // Go back to last valid position
                    break;
                }
            }
            if (targetStepY > step){
                y += deltaY;
                if (ownerHB.isBlocked(objectsHB)) {
                    y -= deltaY;  // Go back to last valid position
                    break;
                }
            }
        }
    }
}

