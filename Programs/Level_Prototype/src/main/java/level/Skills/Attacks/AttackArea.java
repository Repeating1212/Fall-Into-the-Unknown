package level.Skills.Attacks;

import level.Data.Properties.*;

public interface AttackArea {
    boolean isHitBoxInArea(Property property);
    void updatePosition(Position currentPos, Position destinationPos);
}