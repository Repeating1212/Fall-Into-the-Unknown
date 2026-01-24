package level;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import level.Managers.LevelData.LevelConfig;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelScene.fxml"));
        Parent root = loader.load();

        // Set up the stage
        Scene scene = new Scene(root, LevelConfig.MapHeight, LevelConfig.MapWidth);
        primaryStage.setTitle("Protocol");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {launch(args);}
}
