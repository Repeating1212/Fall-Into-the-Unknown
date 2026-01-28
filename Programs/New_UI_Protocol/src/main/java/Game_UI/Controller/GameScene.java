package Game_UI.Controller;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;

public class GameScene {
    @FXML private ImageView Forest, Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal;

    private ImageView[] levels;

    @FXML
    public void initialize() {
        levels = new  ImageView[]{Graveyard, Bridge, Fish_Port, Lake, Mountain, Tower, Dragon, Portal};
        for (ImageView level : levels){
            level.setOpacity(0.5);
        }
    }
}
