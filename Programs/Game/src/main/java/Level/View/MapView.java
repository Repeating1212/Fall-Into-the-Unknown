package Level.View;

import Game_Data.Config.ImageConfig;
import javafx.scene.image.ImageView;

public class MapView {

    private ImageView map;

    public MapView (ImageView map){
        this.map = map;
        this.map.setImage(ImageConfig.LEVEL01_MAP);
    }
}
