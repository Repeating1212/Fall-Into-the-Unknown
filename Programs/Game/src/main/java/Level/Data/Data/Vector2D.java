package Level.Data.Data;

import Level.Data.Properties.Position;

public class Vector2D {
    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Create vector from start position to end position
    public static Vector2D getAttackVector(Position startPos, Position endPos) {
        return new Vector2D(
                (endPos.getX() - startPos.getX()),
                (endPos.getY() - startPos.getY())
        );
    }

    // Normalize to unit vector
    public Vector2D normalize() {
        double length = Math.sqrt(x * x + y * y);
        if (length > 0) {
            return new Vector2D(x / length, y / length);
        }
        return new Vector2D(0, 0);
    }

    // Get angle in radians
    public double getAngle() {
        return Math.atan2(- y, x);
    }

    // Get angle in degrees
    public double getAngleDegrees() {
        return Math.toDegrees(getAngle());
    }

    // Get cardinal direction (for 8-direction games)
    public Direction getDirection() {
        double angle = getAngleDegrees();

        // Map angle to 8 directions
        if (angle >= -22.5 && angle < 22.5) return Direction.RIGHT;
        if (angle >= 22.5 && angle < 67.5) return Direction.UP_RIGHT;
        if (angle >= 67.5 && angle < 112.5) return Direction.UP;
        if (angle >= 112.5 && angle < 157.5) return Direction.UP_LEFT;
        if (angle >= 157.5 || angle < -157.5) return Direction.LEFT;
        if (angle >= -157.5 && angle < -112.5) return Direction.DOWN_LEFT;
        if (angle >= -112.5 && angle < -67.5) return Direction.DOWN;
        if (angle >= -67.5 && angle < -22.5) return Direction.DOWN_RIGHT;

        return Direction.NONE;
    }

    public double getX() { return x; }
    public double getY() { return y; }
}