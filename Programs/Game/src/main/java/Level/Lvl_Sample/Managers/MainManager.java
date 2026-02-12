package Level.Lvl_Sample.Managers;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.Objects.Class_Concrete.Portal;
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
    private final ArrayData<DisplayableObject> displayObj = new ArrayData();
    private final ArrayData<GameObject> gameObj = new ArrayData();

    public MainManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        this.boss = levelSupplier.getBoss(observer);

        displayObj.add(player, boss);
        gameObj.add(player, boss);

        observer.setPlayer(player);

        display(displayObj.get());
        observer.setGameObj(gameObj);
        observer.setDisplayObj(displayObj);
    }

    public void updateObjects(double deltaTime){
        if(!gameRunning) return;
        checkGameCondition();

        for (GameObject gameObject : gameObj.get()){
            gameObject.update(deltaTime, observer);
        }
        removeDead();

        sceneView.updateObjects(displayObj.get());
        sceneView.updatePlayerHeartView(player.getHealth());
        displaySkillCooldown();
        sceneView.updateBossHealthBar(boss.getHealthPercentage());
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
            displayObj.add(portal);
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

        removeDisplay(new ArrayList<DisplayableObject>(toRemove));
    }

    private void display(ArrayList<DisplayableObject> arrayList){
        ArrayList<DisplayableObject> toDisplay = new ArrayList<>();
        for (DisplayableObject object : arrayList){
            toDisplay.addAll(object.getRelatedSprite());
        }
        displayObj.add(toDisplay); // Ignore repeated object
        sceneView.updateObjects(displayObj.get());
    }

    private void removeDisplay(ArrayList<DisplayableObject> arrayList){
        ArrayList<DisplayableObject> toRemove = new ArrayList<>();
        for (DisplayableObject object : arrayList){
            toRemove.addAll(object.getRelatedSprite());
        }
        displayObj.remove(toRemove); // Ignore repeated object
        sceneView.updateObjects(displayObj.get());
    }

    private void displaySkillCooldown(){
        double[] cooldowns = player.getSkillCooldown();
        for (int i = 0; i < cooldowns.length; i++){
            sceneView.updateSkillCooldowns(i ,cooldowns[i]);
        }
    }

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
