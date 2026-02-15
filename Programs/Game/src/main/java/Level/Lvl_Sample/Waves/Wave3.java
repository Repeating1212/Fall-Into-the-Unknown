package Level.Lvl_Sample.Waves;

import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_Sample.Enemy.Object.SlimeKing;

public class Wave3 extends Wave {

    private static final int TOTAL_WAVE = 1;
    private final SlimeKing boss;

    public Wave3(SceneView sceneView, Player player){
        super(sceneView, player, TOTAL_WAVE);
        this.boss = new SlimeKing(getProperties(gameObj));
    }

    @Override
    public boolean isComplete(){
        return gameObj.length() == 1;
    }

    @Override
    protected void spawnEnemies() {
        gameObj.add(boss);
    }

    @Override
    protected double setProgression(){
        return boss.getHealthPercentage();
    }

}
