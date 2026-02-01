package Level.Skills.Player;

import Level.Data.Properties.Position;
import Level.Data.Properties.Property;
import Level.Managers.Observer;
import Level.Skills.Skill;
import Level.Skills.Timer;

public class Defend implements Skill {

    private final Timer defendTimer;
    private final Timer cooldownTimer;
    private final Property owner;

    public Defend(Double cooldown, double defendDuration, Property owner){
        this.defendTimer = new Timer(defendDuration);
        this.cooldownTimer = new Timer(cooldown);
        this.owner = owner;
    }

    public boolean isDefending(){
        return defendTimer.isTicking();
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
            System.out.println("Set Defend");
        }
    }

    public void update(double deltaTime, Observer observer){
        if(defendTimer.isPending()){
            defendTimer.start();
            cooldownTimer.setPending();
        }
        if(defendTimer.isEnd() && cooldownTimer.isPending()){
            cooldownTimer.start();
            System.out.println("Defend finished");
        }
        cooldownTimer.update(deltaTime);
        defendTimer.update(deltaTime);
    }

    public void handleRigid(){
        // Empty
    }
}
