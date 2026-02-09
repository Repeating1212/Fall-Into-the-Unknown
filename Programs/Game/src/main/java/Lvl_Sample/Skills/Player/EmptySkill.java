package Lvl_Sample.Skills.Player;

import Lvl_Sample.Data.Properties.PlayerState;
import Lvl_Sample.Data.Properties.Position;
import Lvl_Sample.Data.Properties.Property;
import Lvl_Sample.Managers.Observer;
import Lvl_Sample.Skills.Skill;

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
