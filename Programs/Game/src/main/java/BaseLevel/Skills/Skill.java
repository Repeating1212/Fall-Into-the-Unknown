package BaseLevel.Skills;

import BaseLevel.Properties.PlayerState;
import BaseLevel.Properties.Position;
import BaseLevel.Properties.Property;
import Lvl_Sample.Managers.Observer;

public interface Skill {

    void update(double deltaTime, Observer observer);
    void activate(double x, double y);
    void activate(Position position);
    void handleRigid();
    double getCooldownPercentage();
    void initializeData(Property owner, PlayerState playerState);
}
