package Level.Lvl_1.Waves;

import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Waves.NewWave.NewWave1;
import Level.Lvl_1.Waves.NewWave.NewWave2;
import Level.Lvl_1.Waves.NewWave.NewWave3;
import Level.Lvl_1.Waves.NewWave.NewWave4;

public class Level01_Wave {

    public static Wave[] getLevel01(SceneView sceneView, Player player){
        return new Wave[]
                {
                new NewWave1(sceneView, player),
                new NewWave2(sceneView, player),
                new NewWave3(sceneView, player),
                new NewWave4(sceneView, player)
                };
    }
}
