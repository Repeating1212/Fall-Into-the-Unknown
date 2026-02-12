package Level.BaseLevel.Manager;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Objects.Class_Concrete.Portal;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Guard;
import Level.Lvl_Sample.Waves.Updater;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Objects;

public class MainManager implements Updater {

    private final SceneView sceneView;
    private final LevelSupplier levelSupplier = new LevelSupplier();
    // Objects
    private final Player player;
    private final Boss boss ;
    private Portal portal;
    // GameState
    private boolean rewardState = false;
    private boolean gameRunning = true;
    // ObjectList
    private final ArrayData<GameObject> gameObj = new ArrayData();

    public MainManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        this.boss = new Guard();

        gameObj.add(levelSupplier.getEnemyWave());
        gameObj.add(player, boss);
        handleDisplay();
    }

    public void updateObjects(double deltaTime){
        if(!gameRunning) return;

        Observer observer = new Observer(player, gameObj);
        checkGameCondition();
        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }
        removeDead();
        handleDisplay();
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

    // Private Method

    private void checkGameCondition() {
        if(isVictory() && ! rewardState){
            portal = new Portal(boss.getProperty());
            gameObj.add(portal);
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

        for (GameObject gameObject : gameObj.get()) {
            if (!gameObject.isHealthNull() && gameObject.isDead()) {
                toRemove.add(gameObject);
            }
        }

        gameObj.remove(toRemove);
    }

    private void displaySkillCooldown(){
        double[] cooldowns = player.getSkillCooldown();
        for (int i = 0; i < cooldowns.length; i++){
            sceneView.updateSkillCooldowns(i ,cooldowns[i]);
        }
    }

    private void handleDisplay(){

        ArrayData<DisplayableObject> toDisplay = new ArrayData<>();

        for (GameObject object : gameObj.get()){
            toDisplay.add(object.getRelatedSprite());
        }

        sceneView.updateObjects(toDisplay.get());
        sceneView.updatePlayerHeartView(player.getHealth());
        displaySkillCooldown();
        sceneView.updateBossHealthBar(boss.getHealthPercentage());
    }

    // Debug usage

    private void addHitBoxDebug(){
        // For Debug purpose
        ArrayList<Rectangle> rectangles = new ArrayList<>();
        for (GameObject object : gameObj.get()) {
            rectangles.add(object.getProperty().getDebugHitBox());
            object.getProperty().showDebugHitBox();
        }
        sceneView.addDebugHitBox(rectangles);
    }
}
