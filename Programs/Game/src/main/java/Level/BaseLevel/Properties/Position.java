package Level.BaseLevel.Properties;

import java.util.ArrayList;
import java.util.Random;

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

    protected void spawnNearBoundary(HitBox hitBox, ArrayList<HitBox> others) {
        int MAP_WIDTH = 1200;
        int MAP_HEIGHT = 675;
        int OFFSET = 10;
        int objWidth = hitBox.getWidth();
        int objHeight = hitBox.getHeight();
        int MAX_ATTEMPTS = 100;

        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            Random random = new Random();
            int edge = random.nextInt(4);

            switch (edge) {
                case 0: // Top
                    x = randomRange(OFFSET, MAP_WIDTH - objWidth - OFFSET);
                    y = OFFSET;
                    break;
                case 1: // Right
                    x = MAP_WIDTH - objWidth - OFFSET;
                    y = randomRange(OFFSET, MAP_HEIGHT - objHeight - OFFSET);
                    break;
                case 2: // Bottom
                    x = randomRange(OFFSET, MAP_WIDTH - objWidth - OFFSET);
                    y = MAP_HEIGHT - objHeight - OFFSET;
                    break;
                case 3: // Left
                    x = OFFSET;
                    y = randomRange(OFFSET, MAP_HEIGHT - objHeight - OFFSET);
                    break;
            }

            if (! hitBox.isCollide(others)){
                return;
            }
        }
    }

    protected void spawnNearBy(HitBox self, ArrayList<HitBox> others, Position targetPosition) {
        int MAX_ATTEMPTS = 100;
        int SEARCH_RADIUS = 50;
        int MAP_WIDTH = 1200;
        int MAP_HEIGHT = 675;
        int OFFSET = 10;

        Random random = new Random();

        // If not collided
        this.x = targetPosition.getX();
        this.y = targetPosition.getY();
        if (!self.isCollide(others)) {
            return;
        }

        //If collided
        for (int attempt = 0; attempt < MAX_ATTEMPTS; attempt++) {
            // Random angle and distance
            double angle = random.nextDouble() * 2 * Math.PI;
            double distance = random.nextDouble() * SEARCH_RADIUS;

            // Calculate new position
            double newX = targetPosition.getX() + Math.cos(angle) * distance;
            double newY = targetPosition.getY() + Math.sin(angle) * distance;

            // Keep within map bounds
            newX = Math.max(OFFSET, Math.min(MAP_WIDTH - self.getWidth() - OFFSET, newX));
            newY = Math.max(OFFSET, Math.min(MAP_HEIGHT - self.getHeight() - OFFSET, newY));

            // Try this position
            this.x = newX;
            this.y = newY;

            if (!self.isCollide(others)) {
                return; // Found a valid position!
            }
        }

        // No suitable position
        spawnNearBoundary(self, others);
    }

    // Private Method

    private int randomRange(int min, int max) {
        Random random = new Random();
        return min + random.nextInt(max - min);
    }
}

