package Lvl_Sample.Skills.Attacks;

import Lvl_Sample.Data.Properties.*;

public interface AttackArea {
    boolean isHitBoxInArea(Property property);
    void updatePosition(Position currentPos, Position destinationPos);
}