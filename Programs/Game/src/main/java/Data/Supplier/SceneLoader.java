package Data.Supplier;

import Data.Interface.OverlayController;
import Data.Interface.SceneInterface;
import LoadFile.FileManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    public enum SceneType {
        // Game UI
        CHARACTER("/Game_UI/FxmlFile/Character.fxml"),
        ENCYCLOPEDIA("/Game_UI/FxmlFile/Encyclopedia.fxml"),
        GAME_DESIGNER("/Game_UI/FxmlFile/GameDesigner.fxml"),
        GAME("/Game_UI/FxmlFile/GameScene.fxml"),
        SETTING("/Game_UI/FxmlFile/Setting.fxml"),
        START("/Game_UI/FxmlFile/StartScene.fxml"),
        // EditorUI
        EQUIPMENT("/EditorUI/FxmlFile/EquipmentScene.fxml"),
        SKILL_MENU("/EditorUI/FxmlFile/SkillMenu.fxml"),
        // Level
        LOSE("/Level/View/lose-screen.fxml"),
        PAUSE("/Level/View/pause-screen.fxml"),
        WIN("/Level/View/win-screen.fxml"),
        LEVEL_01("/Level/LevelScene.fxml");

        private final String path;

        SceneType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public enum PaneType {
        SKILL_PANE("/EditorUI/FxmlFile/SkillPane.fxml"),
        UPGRADE_PANE("/EditorUI/FxmlFile/UpgradePane.fxml"),
        EQUIPMENT_PANE("/EditorUI/FxmlFile/EquipmentPane.fxml");

        private final String path;

        PaneType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    // Unified method for loading overlay scenes
    public static <T> T loadOverlayScene(Pane rootPane, SceneType sceneType, FileManager fileManager) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(sceneType.getPath()));
            Pane overlayPane = loader.load();
            rootPane.getChildren().add(overlayPane);

            Object controller = loader.getController();
            if (controller instanceof OverlayController) {
                ((OverlayController) controller).setRootPane(rootPane);
                ((OverlayController) controller).setFileManager(fileManager);
            }

            return (T) controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Unified method for switching full scenes
    public static void switchScene(Pane rootPane, SceneType sceneType, FileManager fileManager) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(sceneType.getPath()));

            Parent newScene = loader.load();

            Object controller = loader.getController();
            if (controller instanceof SceneInterface) {
                ((SceneInterface) controller).setFileManager(fileManager);
                ((SceneInterface) controller).initializeData();
            }

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadInitialScene(Stage primaryStage, SceneType sceneType) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(sceneType.getPath()));
            Parent root = loader.load();

            // Load File
            FileManager fileManager = new FileManager();
            fileManager.loadFile();
            Object controller = loader.getController();
            if (controller instanceof SceneInterface) {
                ((SceneInterface) controller).setFileManager(fileManager);
                ((SceneInterface) controller).initializeData();
            }

            // Set up the stage
            Scene scene = new Scene(root, 1200, 675);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.setTitle("UI Protocol");
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}