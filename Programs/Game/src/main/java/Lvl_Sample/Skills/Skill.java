package Lvl_Sample.Skills;

import Lvl_Sample.Data.Properties.PlayerState;
import Lvl_Sample.Data.Properties.Position;
import Lvl_Sample.Data.Properties.Property;
import Lvl_Sample.Managers.Observer;

public interface Skill {

    void update(double deltaTime, Observer observer);
    void activate(double x, double y);
    void activate(Position position);
    void handleRigid();
    double getCooldownPercentage();
    void initializeData(Property owner, PlayerState playerState);
}
