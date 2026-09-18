package Data.DataClass;

import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Waves.Level01_Wave;

public enum LevelType {
    LEVEL_01;

    public Wave[] getWaves(SceneView sceneView, Player player){
        return switch (this){
            case LEVEL_01 -> Level01_Wave.getLevel01(sceneView, player);
        };
    }
}

