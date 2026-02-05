package Level;
import Game_Data.Supplier.SceneLoader;
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

        SceneLoader.loadInitialScene(primaryStage, SceneLoader.SceneType.LEVEL_01);
//        Scene scene = new Scene(root, LevelConfig.MapHeight, LevelConfig.MapWidth);
    }

    public static void main(String[] args) {launch(args);}
}
