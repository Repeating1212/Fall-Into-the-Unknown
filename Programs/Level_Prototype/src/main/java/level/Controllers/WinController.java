package level.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WinController {

    @FXML private Pane rootPane;
    @FXML private Text coinLabel;

    @FXML
    public void initialize() {}

    public void updateCoinLabel(int coinValue){
        coinLabel.setText(String.valueOf(coinValue));
    }

    @FXML
    private void handleExitButton() {
        Stage stage = (Stage) rootPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handlePlayAgain() {
        try {
            // Reload the FXML
            Parent gameRoot = FXMLLoader.load(getClass().getResource("/level/LevelScene.fxml"));
            Scene gameScene = new Scene(gameRoot);

            Stage stage = (Stage) rootPane.getScene().getWindow();
            stage.setScene(gameScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
