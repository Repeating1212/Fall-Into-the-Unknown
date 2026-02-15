package Level.BaseLevel.Properties;

import Data.DataClass.ArrayData;
import Data.DataClass.Timer;

import java.util.ArrayList;

public class Status {

    private ArrayData<Timer> pauseMovement = new ArrayData<>();

    public void pauseMovement(double duration){
        Timer timer = new Timer(duration);
        timer.start();
        pauseMovement.add(timer);
    }

    public void update(double deltaTime){
        for (Timer timer : pauseMovement.get()){
            timer.update(deltaTime);
        }

        ArrayList<Timer> toRemove = new ArrayList<>();
        for (Timer timer : pauseMovement.get()){
            if (timer.isEnd()) toRemove.add(timer);
        }
        pauseMovement.remove(toRemove);
    }

    // Getter

    public boolean isPause(){
        return ! pauseMovement.isEmpty();
    }
}
