package Level.Player.Skills.Player;

import Level.Data.Properties.Position;
import Level.Data.Properties.Property;
import Level.Managers.Observer;
import Level.Player.PlayerState;
import Level.Player.Skills.Skill;
import Level.Player.Skills.Timer;

public class Defend implements Skill {

    private final Timer defendTimer;
    private final Timer cooldownTimer;
    private Property owner;
    private PlayerState playerState;

    public Defend(Double cooldown, double defendDuration){
        this.defendTimer = new Timer(defendDuration);
        this.cooldownTimer = new Timer(cooldown);
    }

    public void initializeData(Property owner, PlayerState playerState){
        this.owner = owner;
        this.playerState = playerState;
    }

    public double getCooldownPercentage(){
        return cooldownTimer.getCooldownPercentage();
    }

    public void activate(double mouseX, double mouseY){
        activate(new Position(mouseX, mouseY));
    }

    public void activate(Position position){
        if (cooldownTimer.isDeactive() && defendTimer.isDeactive()){
            defendTimer.setPending();
        }
    }

    public void update(double deltaTime, Observer observer){
        if(defendTimer.isPending()){
            defendTimer.start();
            cooldownTimer.setPending();
            playerState.setDefend(true);
        }
        if(defendTimer.isEnd() && cooldownTimer.isPending()){
            playerState.setDefend(false);
            cooldownTimer.start();
        }
        cooldownTimer.update(deltaTime);
        defendTimer.update(deltaTime);
    }

    public void handleRigid(){
        if (defendTimer.isPending()){
            owner.pauseMovement(defendTimer.getDuration());
        }
    }
}
