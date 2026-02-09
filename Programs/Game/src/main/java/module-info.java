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



    exports Lvl_Sample.View;
    opens Lvl_Sample.View to javafx.fxml;
    exports Lvl_Sample.Data.Properties;
    opens Lvl_Sample.Data.Properties to javafx.fxml;
    exports Lvl_Sample.Objects.Concrete_Class;
    opens Lvl_Sample.Objects.Concrete_Class to javafx.fxml;
    exports Lvl_Sample.Data.Config;
    opens Lvl_Sample to javafx.fxml;
    exports Lvl_Sample.Controllers;
    opens Lvl_Sample.Controllers to javafx.fxml;
    exports Lvl_Sample;
    exports Lvl_Sample.Managers;
    opens Lvl_Sample.Managers to javafx.fxml;
    exports Lvl_Sample.Objects.Base_Class;
    opens Lvl_Sample.Objects.Base_Class to javafx.fxml;
    exports Lvl_Sample.Skills.Attacks;
    opens Lvl_Sample.Skills.Attacks to javafx.fxml;
    exports Lvl_Sample.View.AttackVisualize;
    opens Lvl_Sample.View.AttackVisualize to javafx.fxml;
    exports Lvl_Sample.Managers.LevelData;
    opens Lvl_Sample.Managers.LevelData to javafx.fxml;
    exports Lvl_Sample.Skills;
    opens Lvl_Sample.Skills to javafx.fxml;
    exports Data.Loader;
    opens Data.Loader to com.google.gson, javafx.fxml;

}
