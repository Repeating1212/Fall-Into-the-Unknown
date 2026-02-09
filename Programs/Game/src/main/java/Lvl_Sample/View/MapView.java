package Lvl_Sample.View;

import Data.Loader.ImageLoader;
import javafx.scene.image.ImageView;

public class MapView {

    private ImageView map;

    public MapView (ImageView map){
        this.map = map;
        this.map.setImage(ImageLoader.LEVEL01_MAP);
    }
}
