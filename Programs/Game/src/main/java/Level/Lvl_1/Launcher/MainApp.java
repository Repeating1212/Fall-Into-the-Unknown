package Level.Lvl_1.Launcher;
import Data.Loader.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        SceneLoader.loadInitialScene(primaryStage, SceneLoader.SceneType.LEVEL_01);
//        Scene scene = new Scene(root, LevelConfig.MapHeight, LevelConfig.MapWidth);
    }

    public static void main(String[] args) {launch(args);}
}
