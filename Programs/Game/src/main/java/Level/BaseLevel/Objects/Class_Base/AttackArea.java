package Level.BaseLevel.Objects.Class_Base;

import Level.BaseLevel.Properties.*;

public interface AttackArea {
    boolean isHitBoxInArea(Property property);
    void updatePosition(Position currentPos, Position destinationPos);
}