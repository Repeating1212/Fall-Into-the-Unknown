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

    exports Game_UI.EquipmentScene;
    opens Game_UI.EquipmentScene to javafx.fxml;

    exports LoadFile to com.google.gson;
    opens LoadFile to com.google.gson;
    opens LoadFile.SkillFile to com.google.gson;

    exports Data.Config;
    opens Data.Config to com.google.gson, javafx.fxml;
    exports Data.Supplier;
    opens Data.Supplier to com.google.gson, javafx.fxml;
    exports Data.Interface;
    opens Data.Interface to com.google.gson, javafx.fxml;
    exports Data.Loader;
    opens Data.Loader to com.google.gson, javafx.fxml;

    exports Level.BaseLevel.Controllers;
    opens Level.BaseLevel.Controllers to javafx.fxml;
    exports Level.BaseLevel.View;
    opens Level.BaseLevel.View to javafx.fxml;
    exports Level.BaseLevel.Properties;
    opens Level.BaseLevel.Properties to javafx.fxml;
    exports Level.Lvl_Sample.Enemy.Config;
    exports Level.Lvl_Sample.Managers;
    opens Level.Lvl_Sample.Managers to javafx.fxml;
    exports Level.BaseLevel.View.AttackVisualize;
    opens Level.BaseLevel.View.AttackVisualize to javafx.fxml;
    exports Level.BaseLevel.Skills;
    opens Level.BaseLevel.Skills to javafx.fxml;
    exports Level.BaseLevel.Manager;
    opens Level.BaseLevel.Manager to javafx.fxml;
    exports Level.BaseLevel.Objects.Config;
    exports Level.BaseLevel.Objects.Class_Base;
    opens Level.BaseLevel.Objects.Class_Base to javafx.fxml;
    exports Level.BaseLevel.Objects.Class_Concrete;
    opens Level.BaseLevel.Objects.Class_Concrete to javafx.fxml;
    exports Data.DataClass;
    opens Data.DataClass to com.google.gson, javafx.fxml;
    exports Level.Lvl_Sample.Launcher;
    opens Level.Lvl_Sample.Launcher to javafx.fxml;
    exports Level.Lvl_Sample.Enemy.Object;
    opens Level.Lvl_Sample.Enemy.Object to javafx.fxml;

}
