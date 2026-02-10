package BaseLevel.Objects.Class_Base;

import BaseLevel.Properties.*;

public interface AttackArea {
    boolean isHitBoxInArea(Property property);
    void updatePosition(Position currentPos, Position destinationPos);
}