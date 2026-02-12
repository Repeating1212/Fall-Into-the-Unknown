package Level.Lvl_Sample.Managers;

import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.Lvl_Sample.Enemy.Object.Guard;
import Level.BaseLevel.Manager.LevelSupplier;
import Level.Lvl_Sample.Enemy.Object.Rat;

import java.util.ArrayList;

public class LvlSupplier_Sample extends LevelSupplier {

    public Boss getBoss(Observer observer){
        return new Guard(observer);
    }

    public ArrayList<GameObject> getEnemyWave(Observer observer){
        ArrayList<GameObject> enemies = new ArrayList<>();
        for (int i = 0; i <= 3; i++){
            enemies.add(new Rat());
        }
        return enemies;
    }
}
