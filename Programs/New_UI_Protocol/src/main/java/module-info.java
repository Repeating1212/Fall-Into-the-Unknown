module Protocol.new_ui_protocol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    opens Game_UI to javafx.fxml;
    opens Game_UI.Controller to javafx.fxml;

    exports Game_UI;
    exports Game_UI.Controller;
    exports Game_UI.Controller.Icons_Scene;
    opens Game_UI.Controller.Icons_Scene to javafx.fxml;
}
