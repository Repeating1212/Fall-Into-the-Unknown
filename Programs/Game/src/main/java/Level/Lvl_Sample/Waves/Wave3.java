package Level.Lvl_Sample.Waves;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.Guard;

public class Wave3 extends Wave {

    private static final int TOTAL_WAVE = 1;

    public Wave3(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
    }

    @Override
    public boolean isComplete(){
        return gameObj.length() == 1;
    }

    @Override
    protected void spawnEnemies() {
        gameObj.add(new Guard());
    }
}
