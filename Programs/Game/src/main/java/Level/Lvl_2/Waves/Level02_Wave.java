package Level.Lvl_2.Waves;

import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;

public class Level02_Wave {

    public static Wave[] getWaves(SceneView sceneView, Player player){
        return new Wave[]
                {
//                new TestingWave(sceneView, player),
                new Wave1(sceneView, player),
                new Wave2(sceneView, player),
                };
    }
}
