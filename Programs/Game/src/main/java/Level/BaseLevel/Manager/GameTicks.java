package Level.BaseLevel.Manager;

import Level.Lvl_Sample.Waves.Updater;
import javafx.animation.AnimationTimer;

public class GameTicks {

    private AnimationTimer gameLoop;

    private final Updater manager;
    private final PlayerHandler inputHandle;

    private long lastUpdateTime = 0;
    private boolean isRunning = false;

    public GameTicks(Updater mainManager, PlayerHandler playerHandler) {
        manager = mainManager;
        inputHandle = playerHandler;
    }

    public void start() {
        if (isRunning) return;

        isRunning = true;
        lastUpdateTime = 0;

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (!isRunning) return;

                if (lastUpdateTime == 0) {
                    lastUpdateTime = now;
                    return;
                }

                double deltaTime = (now - lastUpdateTime) / 1_000_000_000.0;
                lastUpdateTime = now;
                manager.updateObjects(deltaTime);
            }
        };
        gameLoop.start();
    }

    public void handlePause() {
        if (gameLoop == null) return;
        if (isRunning){
            isRunning = false;
            inputHandle.setPause();
            lastUpdateTime = 0; // Reset when paused
            gameLoop.stop();

        } else{
            isRunning = true;
            inputHandle.setContinue();
            lastUpdateTime = 0; // Reset when paused
            gameLoop.start();

        }
    }
}