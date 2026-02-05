package Game_UI;

import Game_UI.StartScenes.StartScene;
import LoadFile.FileManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartScenes/StartScene/StartScene.fxml"));
        Parent root = loader.load();

        // Load file data
        FileManager fileManager = new FileManager();
        fileManager.loadFile();
        StartScene controller = loader.getController();
        controller.setFileManager(fileManager);
        controller.initializeData();

        // Set up the stage
        primaryStage.setResizable(false);
        Scene scene = new Scene(root, 1200, 675);
        primaryStage.setTitle("UI Protocol");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
