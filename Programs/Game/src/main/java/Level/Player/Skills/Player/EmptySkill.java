package Level.Player.Skills.Player;

import Level.Player.PlayerState;
import Level.Data.Properties.Position;
import Level.Data.Properties.Property;
import Level.Managers.Observer;
import Level.Player.Skills.Skill;

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
