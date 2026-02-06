package Data.Interface;

import LoadFile.FileManager;
import javafx.scene.layout.Pane;

public abstract class PaneInterface {

    protected FileManager fileManager;
    protected Pane rootPane;

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }

    public void initializeData(){
        // Always run after initialize
        // Override by subclasses
    }

}
