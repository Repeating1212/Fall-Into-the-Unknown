package Level.Data.Config;

import javafx.scene.image.Image;

public class CoinConfig {
    public static final int HEIGHT = 20;
    public static final int WIDTH = 20;
    public static final int SPEED = 0;
    public static final boolean IS_BLOCKABLE = false;

    public static final int RANDOMIZE_SPAWN_MIN = -100;
    public static final int RANDOMIZE_SPAWN_MAX = 100;

    public static Image loadImage() {
        Image image = new Image(CoinConfig.class.getResourceAsStream("/picture/Coin_Animation.gif"));
        if (image.isError()) {
            System.out.println("Coin image not found, using placeholder");
        }
        return image;
    }
}
