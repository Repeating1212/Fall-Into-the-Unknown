module level {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    exports level.View;
    opens level.View to javafx.fxml;
    exports level.Data.Properties;
    opens level.Data.Properties to javafx.fxml;
    exports level.Objects.Concrete_Class;
    opens level.Objects.Concrete_Class to javafx.fxml;
    exports level.Data.Config;
    opens level to javafx.fxml;
    exports level.Controllers;
    opens level.Controllers to javafx.fxml;
    exports level;
    exports level.Managers;
    opens level.Managers to javafx.fxml;
    exports level.Objects.Base_Class;
    opens level.Objects.Base_Class to javafx.fxml;
    exports level.Skills.Attacks;
    opens level.Skills.Attacks to javafx.fxml;
    exports level.View.AttackVisualize;
    opens level.View.AttackVisualize to javafx.fxml;
    exports level.Managers.LevelData;
    opens level.Managers.LevelData to javafx.fxml;
    exports level.Skills;
    opens level.Skills to javafx.fxml;
}