package level.Controllers;

import javafx.animation.AnimationTimer;
import level.Managers.MainManager;

public class GameTicks {

    private AnimationTimer gameLoop;

    private final MainManager manager;

    private long lastUpdateTime = 0;
    private boolean isRunning = false;

    public GameTicks(MainManager mainManager) {
        manager = mainManager;
    }

    public void start() {
        if (isRunning) return;

        isRunning = true;
        lastUpdateTime = 0;

        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
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

    // Not Using Code
//    public void stop() {
//        if (!isRunning) return;
//
//        isRunning = false;
//        if (gameLoop != null) {
//            gameLoop.stop();
//        }
//    }
//
//    public void pause() {
//        if (gameLoop != null) {
//            gameLoop.stop();
//            isRunning = false;
//        }
//    }
//
//    public void resume() {
//        if (gameLoop != null && !isRunning) {
//            gameLoop.start();
//            isRunning = true;
//        }
//    }
//
//    public boolean isRunning() {
//        return isRunning;
//    }
}