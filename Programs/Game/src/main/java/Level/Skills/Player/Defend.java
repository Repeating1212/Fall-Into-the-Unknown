package Level.Skills.Player;

import Level.Skills.Timer;

public class Defend {

    private final Timer defendTimer;
    private final Timer cooldownTimer;

    public Defend(Double cooldown, double defendDuration){
        this.defendTimer = new Timer(defendDuration);
        this.cooldownTimer = new Timer(cooldown);
    }

    public boolean isDefending(){
        return defendTimer.isTicking();
    }

    public double cooldownPercentage(){
        return cooldownTimer.getCooldownPercentage();
    }

    public void setDefend(){
        if (cooldownTimer.isDeactive() && defendTimer.isDeactive()){
            defendTimer.setPending();
            System.out.println("Set Defend");
        }
    }

    public void update(double deltaTime){
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
}
