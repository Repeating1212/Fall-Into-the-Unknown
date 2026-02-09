package Lvl_Sample.Managers;

import Lvl_Sample.Objects.Concrete_Class.Player;
import javafx.scene.shape.Rectangle;
import Lvl_Sample.Managers.LevelData.LevelSupplier;
import Lvl_Sample.Objects.Base_Class.Boss;
import Lvl_Sample.Objects.Base_Class.GameObject;
import Lvl_Sample.Objects.Concrete_Class.Coin;
import Lvl_Sample.Objects.Concrete_Class.Guard;
import Lvl_Sample.Objects.Concrete_Class.Portal;
import Lvl_Sample.View.SceneView;

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

    public MainManager(SceneView sceneView, Player player, Level level, Observer observer){
        this.level = level;
        this.sceneView = sceneView;
        this.observer = observer;
        this.coinManager = new CoinManager(level);

        this.player = player;
        boss = new Guard(player, observer);
        portal = new Portal(observer);
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
