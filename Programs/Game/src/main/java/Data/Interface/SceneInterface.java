package Data.Interface;

import Game_File.FileManager;

public abstract class SceneInterface {
    protected FileManager fileManager;

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void initializeData(){
        // Always run after initialize
        // Override by subclasses
    }
}
