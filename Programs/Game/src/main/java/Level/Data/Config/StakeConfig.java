package Level.Data.Config;

import javafx.scene.image.Image;

public class StakeConfig {

    public static final int HEIGHT = 40;
    public static final int WIDTH = 40;
    public static final int SPEED = 0;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int INITIAL_HEALTH = 20;


    public static Image loadImage() {
        Image image = new Image(StakeConfig.class.getResourceAsStream("/Level/picture/Stake.png"));
        if (image.isError()) {
            System.out.println("Coin image not found, using placeholder");
        }
        return image;
    }
}
