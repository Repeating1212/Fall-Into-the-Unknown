package Data.Interface;

import Game_File.FileManager;
import javafx.scene.layout.Pane;

public abstract class OverlayController {
    protected Pane rootPane;
    protected FileManager fileManager;

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }

    public void setFileManager(FileManager fileManager){
        this.fileManager = fileManager;
    }

}
