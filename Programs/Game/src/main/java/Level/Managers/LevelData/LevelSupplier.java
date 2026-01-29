package Level.Managers.LevelData;

import Level.Objects.Base_Class.Boss;
import Level.Objects.Base_Class.DisplayableObject;
import Level.Objects.Base_Class.GameObject;
import Level.Objects.Concrete_Class.Player;

import java.util.ArrayList;

public class LevelSupplier {
    public static ArrayList<DisplayableObject> getDisplayObjects(Player player, Boss boss){
        ArrayList<DisplayableObject> displayableObjects = new ArrayList<>();
        displayableObjects.add(player);
        displayableObjects.add(player.getAttackVisual());
        displayableObjects.add(boss);
        displayableObjects.add(boss.getAttackVisual());

        return displayableObjects;
    }

    public static ArrayList<GameObject> getCollisionObject(Player player, Boss boss){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boss);
        gameObjects.add(player);

        return gameObjects;
    }

    public static ArrayList<GameObject> getObjects(Player player, Boss boss){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        gameObjects.add(boss);
        gameObjects.add(player);

        return gameObjects;
    }
}
