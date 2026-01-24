module Protocol.new_ui_protocol {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens Protocol.Launcher to javafx.fxml;
    exports Protocol.Launcher;
    exports Protocol.Contoller;
}