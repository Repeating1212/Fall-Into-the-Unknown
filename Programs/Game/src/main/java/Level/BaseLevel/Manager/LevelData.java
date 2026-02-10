package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.View.SceneView;

import java.util.ArrayList;

public class LevelData {

    private final SceneView sceneView;

    // ObjectList
    private final ArrayList<DisplayableObject> displayObj = new ArrayList<>();
    private final ArrayList<GameObject> gameObjects = new ArrayList<>();


    public LevelData(SceneView sceneView){
        this.sceneView = sceneView;
    }

    public ArrayList<DisplayableObject> getDisplayObj(){
        return displayObj;
    }

    public ArrayList<GameObject> getGameObjects(){
        return gameObjects;
    }

    public void addObjects(ArrayList<GameObject> objects){
        addToList(objects, this.gameObjects);
    }

    public void addObjects(GameObject object){
        addToList(object, gameObjects);
    }

    public void removeObjects(ArrayList<GameObject> objects){
        this.gameObjects.removeAll(objects);
    }

    public void removeObjects(GameObject object){
        gameObjects.remove(object);
    }

    public void addDisplay(ArrayList<DisplayableObject> objects){
        addToList(objects, displayObj);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    public void addDisplay(DisplayableObject object){
        addToList(object, displayObj);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    public void removeDisplay(ArrayList<DisplayableObject> objects){
        displayObj.removeAll(objects);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    public void removeDisplay(DisplayableObject object){
        displayObj.remove(object);
        sceneView.updateObjectsInSceneNew(displayObj);
    }

    public void displayObjects(){
        addDisplay(new ArrayList<>(gameObjects));
    }

    public ArrayList<GameObject> getHealthObj(){
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
