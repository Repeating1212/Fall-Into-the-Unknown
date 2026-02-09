package Lvl_Sample.Managers.LevelData;

import Lvl_Sample.Objects.Base_Class.Boss;
import Lvl_Sample.Objects.Base_Class.DisplayableObject;
import Lvl_Sample.Objects.Base_Class.GameObject;
import Lvl_Sample.Objects.Concrete_Class.Player;

import java.util.ArrayList;

public class LevelSupplier {
    public static ArrayList<DisplayableObject> getDisplayObjects(Player player, Boss boss){
        ArrayList<DisplayableObject> displayableObjects = new ArrayList<>();
        displayableObjects.add(player);
        if (player.getAttackVisual() != null){
            displayableObjects.add(player.getAttackVisual());
        }
        displayableObjects.add(boss);
        displayableObjects.add(boss.getAttackVisual());

        return displayableObjects;
    }

    public static ArrayList<GameObject> getObjects(Player player, Boss boss){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boss);
        gameObjects.add(player);

        return gameObjects;
    }
}
