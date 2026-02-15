package Level.BaseLevel.Properties;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;

import java.util.ArrayList;

public class Status {

    private ArrayData<Timer> pauseMovement = new ArrayData<>();
    private ArrayData<Timer> pauseDirect = new ArrayData<>();
    private ArrayData<Timer> invincibilityTimer = new ArrayData<>();
    private ArrayData<Timer> defends = new ArrayData<>();

    private boolean directable = true;

    public void update(double deltaTime) {
        updateTimerList(pauseMovement, deltaTime);
        updateTimerList(pauseDirect, deltaTime);
        updateTimerList(invincibilityTimer, deltaTime);
        updateTimerList(defends, deltaTime);
    }

    private void updateTimerList(ArrayData<Timer> timerList, double deltaTime) {
        timerList.get().forEach(timer -> timer.update(deltaTime));
        timerList.removeIf(Timer::isEnd);
    }

    // Setter

    public void pauseMovement(double duration){
        Timer timer = new Timer(duration, true);
        pauseMovement.add(timer);
    }

    public void pauseDirect(double duration){
        Timer timer = new Timer(duration, true);
        pauseDirect.add(timer);
    }

    public void setInvincible (double duration){
        invincibilityTimer.add(new Timer(duration, true));
    }

    public void setDirectable(boolean directable){
        this.directable = directable;
    }

    public void setDefend(double duration){
        defends.add(new Timer(duration, true));
    }

    // Getter

    public boolean isPauseMovement(){
        return ! pauseMovement.isEmpty();
    }

    public boolean directable() {return pauseDirect.isEmpty() && directable;}

    public boolean isInvincible() {return ! invincibilityTimer.isEmpty();}

    public boolean isDefend() { return ! defends.isEmpty();}
}
