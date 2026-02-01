package Level.Skills;

import Level.Data.Properties.Position;
import Level.Managers.Observer;

public interface Skill {

    void update(double deltaTime, Observer observer);
    void activate(double x, double y);
    void activate(Position position);
    void handleRigid();
    double getCooldownPercentage();
}
