module Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens Game_UI to javafx.fxml;
    exports Game_UI;
    exports Game_UI.GameScenes.Icons_Scene;
    opens Game_UI.GameScenes.Icons_Scene to javafx.fxml;
    exports Game_UI.StartScenes;
    opens Game_UI.StartScenes to javafx.fxml;
    exports Game_UI.GameScenes;
    opens Game_UI.GameScenes to javafx.fxml;
}
