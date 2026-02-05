package Level;
import Game_UI.StartScenes.StartScene;
import Level.Controllers.Controller;
import LoadFile.FileManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Level.Managers.LevelData.LevelConfig;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Load the FXML files
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelScene.fxml"));
        Parent root = loader.load();

        // Load file data
        FileManager fileManager = new FileManager();
        fileManager.loadFile();
        Controller controller = loader.getController();
        controller.setFileManager(fileManager);

        // Set up the stage
        Scene scene = new Scene(root, LevelConfig.MapHeight, LevelConfig.MapWidth);
        primaryStage.setTitle("Protocol");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
