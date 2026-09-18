package Level.Lvl_2.Waves;

import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Waves.NewWave.NewWave1;
import Level.Lvl_1.Waves.NewWave.NewWave2;
import Level.Lvl_1.Waves.NewWave.NewWave3;
import Level.Lvl_1.Waves.NewWave.NewWave4;

public class Level02_Wave {

    public static Wave[] getWaves(SceneView sceneView, Player player){
        return new Wave[]
                {
                new Wave1(sceneView, player),
                new Wave1(sceneView, player),
                };
    }
}
