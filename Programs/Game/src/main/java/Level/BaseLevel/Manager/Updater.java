package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.Lvl_Sample.Managers.Observer;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Objects.Class_Concrete.Portal;
import Level.BaseLevel.View.SceneView;

import java.util.ArrayList;

public abstract class Updater {

    protected final SceneView sceneView;
    // Minor Manager
    protected final LevelData levelData;
    protected final Observer observer;
    // Objects
    protected final Player player;
    protected final Boss boss ;
    protected Portal portal;
    // GameState
    protected boolean rewardState = false;
    protected boolean gameRunning = true;

    public Updater(SceneView sceneView, Player player, LevelData levelData, Observer observer, LevelSupplier levelSupplier){
        this.levelData = levelData;
        this.player = player;
        this.sceneView = sceneView;
        this.observer = observer;
        this.boss = levelSupplier.getBoss(observer);

        levelData.addObjects(levelSupplier.getObjects(player, boss));
        levelData.addObjects(levelSupplier.getEnemyWave(observer));
        levelData.displayObjects();
    }

    public void updateObjects(double deltaTime){
        if(!gameRunning) return;
        checkGameCondition();

        for (GameObject gameObject : levelData.getGameObjects()){
            gameObject.update(deltaTime);
        }
        removeDead();
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
            portal = new Portal(observer, boss.getProperty());
            levelData.addObjects(portal);
            levelData.addDisplay(portal);
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

    private void removeDead() {
        ArrayList<GameObject> toRemove = new ArrayList<>();

        for (GameObject gameObject : levelData.getGameObjects()) {
            if (!gameObject.isHealthNull() && gameObject.isDead()) {
                toRemove.add(gameObject);
            }
        }

        levelData.removeObjects(toRemove);

        ArrayList<DisplayableObject> toDisappear = new ArrayList<>();
        for (GameObject gameObject : toRemove){
            toDisappear.addAll(gameObject.getRelatedSprite());
        }
        levelData.removeDisplay(toDisappear);
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
