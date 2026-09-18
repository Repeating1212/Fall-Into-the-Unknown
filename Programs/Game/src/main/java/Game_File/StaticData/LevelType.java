package Game_File.StaticData;

import Level.BaseLevel.Manager.Wave;
import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.Lvl_1.Waves.Level01_Wave;
import Level.Lvl_2.Waves.Level02_Wave;

public enum LevelType {
    LEVEL_01, LEVEL_02;

    public Wave[] getWaves(SceneView sceneView, Player player){
        return switch (this){
            case LEVEL_01 -> Level01_Wave.getWaves(sceneView, player);
            case LEVEL_02 -> Level02_Wave.getWaves(sceneView, player);
        };
    }
}

