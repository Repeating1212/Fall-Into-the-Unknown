module sample.sample_code {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.base;
    requires javafx.graphics;

    opens sample.sample_code to javafx.fxml;
    exports sample.sample_code;
}