package BaseLevel.Manager;

import BaseLevel.Objects.Class_Base.Boss;
import BaseLevel.Objects.Class_Base.DisplayableObject;
import BaseLevel.Objects.Class_Base.GameObject;
import BaseLevel.Objects.Class_Concrete.Player;
import Lvl_Sample.Managers.Observer;

import java.util.ArrayList;

public abstract class LevelSupplier {
    public ArrayList<DisplayableObject> getDisplayObjects(Player player, Boss boss){
        ArrayList<DisplayableObject> displayableObjects = new ArrayList<>();
        displayableObjects.add(player);
        if (player.getAttackVisual() != null){
            displayableObjects.add(player.getAttackVisual());
        }
        displayableObjects.add(boss);
        if (boss.getAttackVisual() != null){
            displayableObjects.add(boss.getAttackVisual());
        }

        return displayableObjects;
    }

    public ArrayList<GameObject> getObjects(Player player, Boss boss){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boss);
        gameObjects.add(player);

        return gameObjects;
    }

    public abstract Boss getBoss(Observer observer);
}
