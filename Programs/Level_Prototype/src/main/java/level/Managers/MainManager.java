package level.Managers;

import javafx.scene.shape.Rectangle;
import level.Controllers.PlayerHandler;
import level.Managers.LevelData.LevelSupplier;
import level.Objects.Base_Class.Boss;
import level.Objects.Base_Class.GameObject;
import level.Objects.Concrete_Class.Coin;
import level.Objects.Concrete_Class.Guard;
import level.Objects.Concrete_Class.Player;
import level.Objects.Concrete_Class.Portal;
import level.View.SceneView;

import java.util.ArrayList;

public class MainManager {

    private final SceneView sceneView;
    // Minor Manager
    private final Level level;
    private final Observer observer;
    private final CoinManager coinManager;
    // Objects
    private final Player player;
    private final Boss boss ;
    private final Portal portal;
    // GameState
    private boolean rewardState = false;
    private boolean gameRunning = true;

    public MainManager(SceneView sceneView, PlayerHandler playerHandler){
        level = new Level(sceneView);
        this.sceneView = sceneView;
        observer = new Observer(level, sceneView);
        this.coinManager = new CoinManager(level);

        player = new Player(observer);
        boss = new Guard(player, observer);
        portal = new Portal(observer);

        playerHandler.initialize(player);
        observer.setPlayer(player);

        level.addObjects(LevelSupplier.getObjects(player, boss));
        level.addDisplay(LevelSupplier.getDisplayObjects(player, boss));
    }

    public void updateObjects(double deltaTime ) {
        if(!gameRunning) return;
        for (GameObject gameObject : level.getGameObjects()){
            gameObject.update(deltaTime);
        }

        if(isVictory() && ! rewardState){
            handleWinCondition();
            rewardState = true;
        }
        else if(isLose()){
            sceneView.showLoseScreen();
            gameRunning = false;

        }
        else if (rewardState && !isEnd()) {
            coinManager.handleCoinCollection();
            sceneView.setMoneyValue(coinManager.getMoneyValue());

        }
        else if(rewardState && isEnd()){
            sceneView.showWinScreen(coinManager.getMoneyValue());
            rewardState = false;
            gameRunning = false;
        }
    }

    // Private Method

    private void handleWinCondition(){
        closeBoss();
        spawnCoin();
        spawnPortal();
    }

    private void closeBoss(){
        level.removeDisplay(boss);
        level.removeDisplay(boss.getAttackVisual());
        level.removeObjects(boss);
    }

    private void spawnCoin(){
        ArrayList<Coin> coins = coinManager.spawnCoins(boss.getProperty(), observer);
        level.addDisplay(new ArrayList<>(coins));
        level.addObjects(new ArrayList<>(coins));
    }

    private boolean isVictory(){
        return (player.isAlive() && boss.isDead() && gameRunning);
    }

    private boolean isLose(){
        return (player.isDead() && gameRunning);
    }

    private boolean isEnd(){
        return (portal.isDead() && gameRunning);
    }

    private void spawnPortal(){
        portal.setPosition(boss.getX(), boss.getY());
        level.addDisplay(portal);
        level.addObjects(portal);
    }

    private void addHitBoxDebug(){
        // For Debug purpose
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (GameObject object : level.getGameObjects()) {
            rectangles.add(object.getProperty().getDebugHitBox());
            object.getProperty().showDebugHitBox();
        }
        sceneView.addDebugHitBox(rectangles);
    }
}
