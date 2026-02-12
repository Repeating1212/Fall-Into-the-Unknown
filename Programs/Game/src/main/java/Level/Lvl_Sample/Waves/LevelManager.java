package Level.Lvl_Sample.Waves;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SceneView;

public class LevelManager implements Updater{

    protected final Player player;
    protected final SceneView sceneView;
    protected final ArrayData<GameObject> gameObj = new ArrayData();
    protected boolean gamePause = false;

    private final Wave1 wave1;
    private final Wave2 wave2;
    private int currentWave = 0;

    public LevelManager(SceneView sceneView, Player player){
        this.player = player;
        this.sceneView = sceneView;
        gameObj.add(player);

        this.wave1 = new Wave1(sceneView, player);
        this.wave2 = new Wave2(sceneView, player);
    }

    public void updateObjects(double deltaTime){

        if (gamePause) return;

        if (currentWave == 0){
            wave1.updateObjects(deltaTime);
        } else {
            wave2.updateObjects(deltaTime);
        }

        if (wave1.isComplete() && currentWave == 0){
            currentWave ++;
        } else if (wave2.isComplete() && currentWave == 1){
            System.out.println("Win");
            sceneView.showWinScreen(5);
            gamePause = true;
        }

        if (wave1.isLose() || wave2.isLose()){
            sceneView.showLoseScreen();
            gamePause = true;
        }
    }

}
