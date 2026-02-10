package Level.Player.Skills.Attacks;

import Level.Data.Properties.*;

public interface AttackArea {
    boolean isHitBoxInArea(Property property);
    void updatePosition(Position currentPos, Position destinationPos);
}