package Data.DataClass;

import BaseLevel.Properties.Position;

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

    public double getX() { return x; }
    public double getY() { return y; }
}