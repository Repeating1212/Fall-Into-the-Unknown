package Level.Skills;

import Level.Data.Properties.PlayerState;
import Level.Data.Properties.Position;
import Level.Data.Properties.Property;
import Level.Managers.Observer;

public interface Skill {

    void update(double deltaTime, Observer observer);
    void activate(double x, double y);
    void activate(Position position);
    void handleRigid();
    double getCooldownPercentage();
    void initializeData(Property owner, PlayerState playerState);
}
