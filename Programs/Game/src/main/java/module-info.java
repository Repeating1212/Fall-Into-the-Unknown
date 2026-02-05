module Game {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    requires com.google.gson;

    opens Game_UI to javafx.fxml;
    exports Game_UI;
    exports Game_UI.StartScene;
    opens Game_UI.StartScene to javafx.fxml;
    exports Game_UI.GameScene;
    opens Game_UI.GameScene to javafx.fxml;

    exports EditorUI;
    opens EditorUI to javafx.fxml;

    exports LoadFile to com.google.gson;
    opens LoadFile to com.google.gson;
    opens LoadFile.SkillFile to com.google.gson;

    exports Game_Data;
    opens Game_Data to com.google.gson, javafx.fxml;
    exports Game_Data.Config;
    opens Game_Data.Config to com.google.gson, javafx.fxml;
    exports Game_Data.Supplier;
    opens Game_Data.Supplier to com.google.gson, javafx.fxml;
    exports Game_Data.Interface;
    opens Game_Data.Interface to com.google.gson, javafx.fxml;



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
