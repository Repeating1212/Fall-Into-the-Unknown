package BaseLevel.Manager;

import Lvl_Sample.Managers.Observer;
import BaseLevel.Objects.Class_Base.Boss;
import BaseLevel.Objects.Class_Base.GameObject;
import BaseLevel.Objects.Class_Concrete.Player;
import BaseLevel.Objects.Class_Concrete.Portal;
import BaseLevel.View.SceneView;

public abstract class Updater {

    protected final SceneView sceneView;
    // Minor Manager
    protected final LevelData levelData;
    protected final Observer observer;
    // Objects
    protected final Player player;
    protected final Boss boss ;
    protected final Portal portal;
    // GameState
    protected boolean rewardState = false;
    protected boolean gameRunning = true;

    public Updater(SceneView sceneView, Player player, LevelData levelData, Observer observer, LevelSupplier levelSupplier){
        this.levelData = levelData;
        this.player = player;
        this.sceneView = sceneView;
        this.observer = observer;
        this.boss = levelSupplier.getBoss(observer);

        portal = new Portal(observer);

        levelData.addObjects(levelSupplier.getObjects(player, boss));
        levelData.addDisplay(levelSupplier.getDisplayObjects(player, boss));
    }

    public void updateObjects(double deltaTime){
        if(!gameRunning) return;
        checkGameCondition();

        for (GameObject gameObject : levelData.getGameObjects()){
            gameObject.update(deltaTime);
        }
    }

    protected boolean isVictory(){
        return (player.isAlive() && boss.isDead() && gameRunning);
    }

    protected boolean isLose(){
        return (player.isDead() && gameRunning);
    }

    protected boolean isEnd(){
        return (portal.isDead() && gameRunning);
    }

    // Abstract Method

    abstract protected void handleWinCondition();

    // Private Method

    private void checkGameCondition() {
        if(isVictory() && ! rewardState){
            handleWinCondition();
            rewardState = true;
        }
        else if(isLose()){
            sceneView.showLoseScreen();
            gameRunning = false;

        }
        else if(rewardState && isEnd()){
            sceneView.showWinScreen(5);
            rewardState = false;
            gameRunning = false;
        }
    }

//    private void spawnPortal(){
//        portal.setPosition(boss.getX(), boss.getY());
//        levelData.addDisplay(portal);
//        levelData.addObjects(portal);
//    }
//
//    private void addHitBoxDebug(){
//        // For Debug purpose
//        ArrayList<Rectangle> rectangles = new ArrayList<>();
//        for (GameObject object : levelData.getGameObjects()) {
//            rectangles.add(object.getProperty().getDebugHitBox());
//            object.getProperty().showDebugHitBox();
//        }
//        sceneView.addDebugHitBox(rectangles);
//    }
}
