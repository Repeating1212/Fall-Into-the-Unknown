package Level.BaseLevel.Skills;

import Level.BaseLevel.Properties.PlayerState;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Manager.Observer;

public class EmptySkill implements Skill {

    public EmptySkill(){
    }
    public void initializeData(Property owner, PlayerState playerState) {
        // Empty
    }

    public double getCooldownPercentage(){
        return 1;
    }

    public void activate(double mouseX, double mouseY){
        // Empty
    }

    public void activate(Position position){
        // Empty
    }

    public void update(double deltaTime, Observer observer){
        // Empty
    }

    public void handleRigid(){
        // Empty
    }
}
