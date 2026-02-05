package Game_Data;

import LoadFile.FileManager;

public class GameInitialize {

    public static FileManager getNewFile(){
        FileManager fileManager = new FileManager();
        fileManager.loadFile();
        return fileManager;
    }


}
