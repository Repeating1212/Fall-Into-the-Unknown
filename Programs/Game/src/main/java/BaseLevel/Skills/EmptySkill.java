package BaseLevel.Skills;

import BaseLevel.Properties.PlayerState;
import BaseLevel.Properties.Position;
import BaseLevel.Properties.Property;
import Lvl_Sample.Managers.Observer;

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
