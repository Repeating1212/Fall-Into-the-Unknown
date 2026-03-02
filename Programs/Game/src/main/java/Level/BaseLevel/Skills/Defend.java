package Level.BaseLevel.Skills;

import Data.DataClass.Timer;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Properties.PlayerState;

public class Defend implements Skill {

    private final Timer defendTimer;
    private final Timer cooldownTimer;
    private Property owner;
//    private PlayerState playerState;

    public Defend(Double cooldown, double defendDuration){
        this.defendTimer = new Timer(defendDuration);
        this.cooldownTimer = new Timer(cooldown);
    }

    public void initializeData(Property owner, PlayerState playerState){
        this.owner = owner;
//        this.playerState = playerState;
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
            owner.setDefend(defendTimer.getDuration());
            owner.pauseMovement(defendTimer.getDuration() * 0.2);
        }
        if(defendTimer.isEnd() && cooldownTimer.isPending()){
            cooldownTimer.start();
        }
        cooldownTimer.update(deltaTime);
        defendTimer.update(deltaTime);
    }

    public void handleRigid(){}
}
