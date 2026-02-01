package Game_UI;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneLoader {

    // Enum for scene types
    public enum SceneType {
        SETTING("/Game_UI/GameScenes/Setting/Setting.fxml"),
        CHARACTER("/Game_UI/Icons_Scene/CharacterScene/UnknownScene.fxml"),
        ENCYCLOPEDIA("/Game_UI/Icons_Scene/EncyclopediaScene/UnknownScene.fxml"),
        STORE("/Game_UI/Icons_Scene/StoreScene/UnknownScene.fxml"),
        SKILL("/Game_UI/Icons_Scene/SkillScene/SkillScene.fxml"),
        GAME("/Game_UI/GameScenes/GameScene/GameScene.fxml"),
        GAME_DESIGNER("/Game_UI/StartScenes/GameDesignerScene/UnknownScene.fxml"),
        START("/Game_UI/StartScenes/StartScene/StartScene.fxml"),
        LEVEL_01("/Level/LevelScene.fxml");

        private final String path;

        SceneType(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    // Unified method for loading overlay scenes
    public static <T> T loadOverlayScene(Pane rootPane, SceneType sceneType) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneLoader.class.getResource(sceneType.getPath()));
            Pane overlayPane = loader.load();
            rootPane.getChildren().add(overlayPane);

            Object controller = loader.getController();
            if (controller instanceof OverlayController) {
                ((OverlayController) controller).setRootPane(rootPane);
            }

            return (T) controller;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Unified method for switching full scenes
    public static void switchScene(Pane rootPane, SceneType sceneType) {
        try {
            Parent newScene = FXMLLoader.load(SceneLoader.class.getResource(sceneType.getPath()));
            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(new Scene(newScene));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}