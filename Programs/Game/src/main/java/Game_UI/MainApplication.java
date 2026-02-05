package Game_UI;

import Data.Supplier.SceneLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApplication extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneLoader.loadInitialScene(primaryStage, SceneLoader.SceneType.START);
    }

    public static void main(String[] args) {launch(args);}
}
