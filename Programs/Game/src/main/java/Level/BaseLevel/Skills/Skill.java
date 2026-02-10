package Level.BaseLevel.Skills;

import Level.BaseLevel.Properties.PlayerState;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Managers.Observer;

public interface Skill {

    void update(double deltaTime, Observer observer);
    void activate(double x, double y);
    void activate(Position position);
    void handleRigid();
    double getCooldownPercentage();
    void initializeData(Property owner, PlayerState playerState);
}
