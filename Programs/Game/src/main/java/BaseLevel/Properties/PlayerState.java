package BaseLevel.Properties;

import Data.DataClass.Timer;

public class PlayerState {

    private boolean isDefend;
    private final Timer invincibilityTimer; // For hitting effect InvincibilityFrames


    public PlayerState(double invincibilityPeriod){
        invincibilityTimer = new Timer(invincibilityPeriod);
    }

    public void setDefend(boolean state){
        isDefend = state;
    }

    public void setDamaged(){
        invincibilityTimer.start();
    }

    public void update(double deltaTime){
        invincibilityTimer.update(deltaTime);
    }

    public boolean isInvincibility(){
        return invincibilityTimer.isTicking();
    }

    // Handle condition

    public boolean isDamageable(){
        return (! isDefend && invincibilityTimer.isEnd());
    }
}
