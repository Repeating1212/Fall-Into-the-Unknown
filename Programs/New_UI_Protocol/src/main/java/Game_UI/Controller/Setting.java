package Game_UI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Setting {
    @FXML
    private Pane SettingPane;
    private Pane rootPane;
    @FXML
    public void initialize() {}

    public void setRootPane(Pane rootPane){
        this.rootPane = rootPane;
    }

    @FXML
    private void handleReturn() {
        try {
            // Reload the FXML
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/Game_UI/StartScene/StartScene.fxml"));
            Scene startScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(startScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClose() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(SettingPane);
    }
}
