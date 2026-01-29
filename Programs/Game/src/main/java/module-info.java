module Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;

    opens Game_UI to javafx.fxml;
    exports Game_UI;
    exports Game_UI.GameScenes.Icons_Scene;
    opens Game_UI.GameScenes.Icons_Scene to javafx.fxml;
    exports Game_UI.StartScenes;
    opens Game_UI.StartScenes to javafx.fxml;
    exports Game_UI.GameScenes;
    opens Game_UI.GameScenes to javafx.fxml;


    exports Level.View;
    opens Level.View to javafx.fxml;
    exports Level.Data.Properties;
    opens Level.Data.Properties to javafx.fxml;
    exports Level.Objects.Concrete_Class;
    opens Level.Objects.Concrete_Class to javafx.fxml;
    exports Level.Data.Config;
    opens Level to javafx.fxml;
    exports Level.Controllers;
    opens Level.Controllers to javafx.fxml;
    exports Level;
    exports Level.Managers;
    opens Level.Managers to javafx.fxml;
    exports Level.Objects.Base_Class;
    opens Level.Objects.Base_Class to javafx.fxml;
    exports Level.Skills.Attacks;
    opens Level.Skills.Attacks to javafx.fxml;
    exports Level.View.AttackVisualize;
    opens Level.View.AttackVisualize to javafx.fxml;
    exports Level.Managers.LevelData;
    opens Level.Managers.LevelData to javafx.fxml;
    exports Level.Skills;
    opens Level.Skills to javafx.fxml;
}
