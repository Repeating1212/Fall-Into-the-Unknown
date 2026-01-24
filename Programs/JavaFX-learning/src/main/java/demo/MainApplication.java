package demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;

public class MainApplication extends Application {
    @Override
    public void start (Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        URL url = getClass().getResource("sample.fxml");
        loader.setLocation(url);
        Controller controller = loader.getController();

        Button button1 = new Button("Button 1");
        Button button2 = new Button("Button 2");
        button1.setStyle("-fx-background-color: #0000ff");
        VBox vbox = new VBox(button1, button2);
        Scene scene = new Scene(vbox);
        scene.getStylesheets().add("style1/button-styles.css");

        primaryStage.setScene(scene);
        primaryStage.setHeight(400);
        primaryStage.setWidth(400);
        primaryStage.show();
    }

    public static void main(String[] args){
        Application.launch(args);
    }
}
