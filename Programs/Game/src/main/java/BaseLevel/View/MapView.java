package BaseLevel.View;

import Data.Loader.ImageLoader;
import javafx.scene.image.ImageView;

public class MapView {

    private ImageView map;

    public MapView (ImageView map){
        this.map = map;
        this.map.setImage(ImageLoader.L01_MAP);
    }
}
