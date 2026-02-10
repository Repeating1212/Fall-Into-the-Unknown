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



    exports BaseLevel.View;
    opens BaseLevel.View to javafx.fxml;
    exports BaseLevel.Properties;
    opens BaseLevel.Properties to javafx.fxml;
    exports Lvl_Sample.Enemy;
    opens Lvl_Sample.Enemy to javafx.fxml;
    exports Lvl_Sample.Data.Config;
    opens Lvl_Sample to javafx.fxml;
    exports BaseLevel.Controllers;
    opens BaseLevel.Controllers to javafx.fxml;
    exports Lvl_Sample;
    exports Lvl_Sample.Managers;
    opens Lvl_Sample.Managers to javafx.fxml;
    exports BaseLevel.View.AttackVisualize;
    opens BaseLevel.View.AttackVisualize to javafx.fxml;
    exports BaseLevel.Skills;
    opens BaseLevel.Skills to javafx.fxml;
    exports Data.Loader;
    opens Data.Loader to com.google.gson, javafx.fxml;
    exports BaseLevel.Manager;
    opens BaseLevel.Manager to javafx.fxml;
    exports BaseLevel.Objects.Config;
    exports BaseLevel.Objects.Class_Base;
    opens BaseLevel.Objects.Class_Base to javafx.fxml;
    exports BaseLevel.Objects.Class_Concrete;
    opens BaseLevel.Objects.Class_Concrete to javafx.fxml;
    exports Data.DataClass;
    opens Data.DataClass to com.google.gson, javafx.fxml;

}
