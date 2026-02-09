package Lvl_Sample.Skills.Attacks;

import Lvl_Sample.Data.Properties.Position;
import Lvl_Sample.Data.Properties.Property;

public class ConeAttackArea implements AttackArea {
    private final double RADIUS;
    private final double AREA_ANGLE;

    private Position startPos = new Position();
    private Position endPos = new Position();

    public ConeAttackArea(double radius, double attackAngle) {
        this.RADIUS = radius;
        this.AREA_ANGLE = attackAngle;
    }

    @Override
    public void updatePosition(Position currentPos, Position destinationPos) {
        this.startPos = currentPos;
        this.endPos = destinationPos;
    }

    @Override
    public boolean isHitBoxInArea(Property object) {
        for (Position corner : object.getCorner()) {
            if (isPointInArea(corner.getX(), corner.getY())) {
                return true;
            }
        }
        return false;
    }

    // Private Method

    private boolean isPointInArea(double cornerX, double cornerY) {
        // Calculate distance
        double dx = cornerX - startPos.getX();
        double dy = -(cornerY - startPos.getY());
        double distance = Math.sqrt(dx * dx + dy * dy);
        // Calculate direction
        Vector2D toPoint = new Vector2D(dx, dy).normalize();
        Vector2D direction = Vector2D.getAttackVector(startPos, endPos).normalize();
        // Calculate angle between direction vector and point vector
        double dotProduct = direction.getX() * toPoint.getX() + direction.getY() * toPoint.getY();
        double pointAngle = Math.toDegrees(Math.acos(dotProduct));

        return (distance <= RADIUS &&
                pointAngle <= AREA_ANGLE / 2);
    }
}