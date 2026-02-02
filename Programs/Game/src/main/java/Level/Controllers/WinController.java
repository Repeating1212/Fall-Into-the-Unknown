package Level.Controllers;

import Game_Data.ImageLoader;
import LoadFile.DataManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class WinController {

    @FXML private Pane rootPane;
    @FXML private Text coinLabel;
    @FXML private ImageView coinView;

    @FXML
    public void initialize() {
        coinView.setImage(ImageLoader.COIN);
    }

    public void updateCoinLabel(int coinValue){
        coinLabel.setText(String.valueOf(coinValue));
        DataManager.getGameFile().addCoins(coinValue);
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        try {
            Button clickedButton = (Button) event.getSource();
            Parent secondScene = FXMLLoader.load(getClass().getResource("/Game_UI/GameScenes/GameScene/GameScene.fxml"));
            Stage stage = (Stage) clickedButton.getScene().getWindow();

            // Set new scene
            Scene scene = new Scene(secondScene);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
