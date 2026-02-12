package Level.Lvl_Sample.Managers;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Objects.Class_Concrete.Portal;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Level.BaseLevel.View.SceneView;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class MainManager {

    private final SceneView sceneView;
    private final LvlSupplier_Sample levelSupplier = new LvlSupplier_Sample();
    // Minor Manager
    private final Observer observer = new Observer();
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
        this.boss = levelSupplier.getBoss(observer);

        gameObj.add(player, boss);
        handleDisplay();

        observer.setPlayer(player);
        observer.setGameObj(gameObj);
    }

    public void updateObjects(double deltaTime){
        if(!gameRunning) return;
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
            portal = new Portal(observer, boss.getProperty());
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
