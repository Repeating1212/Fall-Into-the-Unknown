module Protocol.new_ui_protocol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;

    exports Game_UI.Controller;
    exports Game_UI;
    opens Game_UI to javafx.fxml;
}