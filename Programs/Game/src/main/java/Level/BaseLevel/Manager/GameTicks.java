package Level.BaseLevel.Manager;

import Data.DataClass.LevelType;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import javafx.animation.AnimationTimer;

public class GameTicks {

    private AnimationTimer gameLoop;

    private final Updater manager;
    private final InputHandler inputHandler;

    private long lastUpdateTime = 0;
    private boolean isRunning = false;

    public GameTicks(SceneView sceneView, Player player, InputHandler inputHandler) {
        this.manager = new LevelManager(sceneView, player, LevelType.LEVEL_01);
        this.inputHandler = inputHandler;
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
                inputHandler.updateInput();
                manager.updateObjects(deltaTime);
            }
        };
        gameLoop.start();
    }

    public void handlePause() {
        if (gameLoop == null) return;
        if (isRunning){
            isRunning = false;
            lastUpdateTime = 0; // Reset when paused
            gameLoop.stop();

        } else{
            isRunning = true;
            lastUpdateTime = 0; // Reset when paused
            gameLoop.start();

        }
    }
}