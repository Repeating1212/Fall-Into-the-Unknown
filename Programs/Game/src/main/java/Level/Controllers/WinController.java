package Level.Controllers;

import Data.Interface.OverlayController;
import Data.Supplier.ImageLoader;
import Data.Supplier.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class WinController extends OverlayController {

    @FXML private Pane rootPane;
    @FXML private Text coinLabel;
    @FXML private ImageView coinView;

    @FXML
    public void initialize() {
        coinView.setImage(ImageLoader.COIN);
    }

    public void updateCoinLabel(int coinValue){
        coinLabel.setText(String.valueOf(coinValue));
        fileManager.getGameFile().addCoins(coinValue);
    }

    @FXML
    private void handleExitButton(ActionEvent event) {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.GAME, fileManager);
    }

    @FXML
    private void handlePlayAgain() {
        SceneLoader.switchScene(rootPane, SceneLoader.SceneType.LEVEL_01, fileManager);
    }
}
