package Level.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MapView {

    private ImageView map;

    public MapView (ImageView map){
        this.map = map;

        // Load images
        Image mapImg = new Image(getClass().getResourceAsStream("/picture/map.png"));
        this.map.setImage(mapImg);
    }
}
