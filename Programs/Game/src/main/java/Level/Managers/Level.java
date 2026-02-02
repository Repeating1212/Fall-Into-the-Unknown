package Level.Managers;

import Level.Objects.Base_Class.DisplayableObject;
import Level.Objects.Base_Class.GameObject;
import Level.View.SceneView;

import java.util.ArrayList;

public class Level {

    private final SceneView sceneView;

    // ObjectList
    private final ArrayList<DisplayableObject> displayObj = new ArrayList<>();
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();


    public Level(SceneView sceneView){
        this.sceneView = sceneView;
    }

    protected  ArrayList<DisplayableObject> getDisplayObj(){
        return displayObj;
    }

    protected ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    protected void addObjects(ArrayList<GameObject> objects){
        addToList(objects, this.gameObjects);
    }

    protected void addObjects(GameObject object){
        addToList(object, gameObjects);
    }

    protected void removeObjects(ArrayList<GameObject> objects){
        this.gameObjects.removeAll(objects);
    }

    protected void removeObjects(GameObject object){
        gameObjects.remove(object);
    }

    protected void addDisplay(ArrayList<DisplayableObject> objects){
        addToList(objects, displayObj);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    protected void addDisplay(DisplayableObject object){
        addToList(object, displayObj);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    protected void removeDisplay(ArrayList<DisplayableObject> objects){
        displayObj.removeAll(objects);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    protected void removeDisplay(DisplayableObject object){
        displayObj.remove(object);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    protected ArrayList<GameObject> getHealthObj(){
        ArrayList<GameObject> gameObjects = new ArrayList<GameObject>();
        for (GameObject object : this.gameObjects){
            if(! object.isHealthNull()){
                gameObjects.add(object);
            }
        }
        return gameObjects;
    }

    // Private method

    private <T> void addToList(ArrayList<T> toAdd, ArrayList<T> beAdded) {
        if (toAdd == null || beAdded == null) return;

        for (T element : toAdd) {
            if (!beAdded.contains(element)) {
                beAdded.add(element);
            }
        }
    }

    private <T> void addToList(T toAdd, ArrayList<T> beAdded) {
        if (toAdd == null || beAdded == null) return;
        if (!beAdded.contains(toAdd)) {
            beAdded.add(toAdd);
        }
    }
}
