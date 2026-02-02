package Level.View;

import Game_Data.Config.ImageConfig;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerHeartView {

    private final ImageView[] hearts;

    public PlayerHeartView (ImageView[] hearts){
        this.hearts = hearts;
    }

    // Method to update health display
    public void updatePlayerHeartView(int health) {
        int currentHealth = Math.min(8, Math.max(0, health)); // Clamp between 0-8

        for (int i = 0; i < 8; i++) {
            if (i < currentHealth) {
                hearts[i].setImage(ImageConfig.HEART_FULL);  // Lighted icon
            } else {
                hearts[i].setImage(ImageConfig.HEART_EMPTY); // Dark icon
            }
        }
    }

}
