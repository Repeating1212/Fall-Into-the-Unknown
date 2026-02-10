package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Class_Base.Boss;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.Lvl_Sample.Managers.Observer;

import java.util.ArrayList;

public abstract class LevelSupplier {
    public ArrayList<GameObject> getObjects(Player player, Boss boss){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boss);
        gameObjects.add(player);

        return gameObjects;
    }

    public abstract Boss getBoss(Observer observer);

    public abstract ArrayList<GameObject> getEnemyWave(Observer observer);
}
