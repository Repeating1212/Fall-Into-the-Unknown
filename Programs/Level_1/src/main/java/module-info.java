module protocol.level_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens Lvl_01 to javafx.fxml;
    exports Lvl_01;
}