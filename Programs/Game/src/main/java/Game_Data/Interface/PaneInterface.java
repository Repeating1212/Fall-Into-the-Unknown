package Game_Data.Interface;

import LoadFile.FileManager;

public abstract class PaneInterface {

    protected FileManager fileManager;

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

}
