package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.Lvl_Sample.Enemy.Object.Guard;
import Level.Lvl_Sample.Enemy.Object.Rat;

import java.util.ArrayList;

public class LevelSupplier {

    public ArrayList<GameObject> getEnemyWave(){
        ArrayList<GameObject> enemies = new ArrayList<>();
        for (int i = 0; i <= 3; i++){
            enemies.add(new Rat());
        }
        return enemies;
    }
}
