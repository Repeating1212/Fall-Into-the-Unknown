package Level.View;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PlayerHeartView {
    private static Image heartFull;
    private static Image heartEmpty;

    @FXML
    private static ImageView heart1, heart2, heart3, heart4, heart5, heart6, heart7, heart8;
    private static int currentHealth = 8;
    private static ImageView[] hearts;

    public PlayerHeartView (ImageView[] hearts){
        this.hearts = hearts;
        // Load images
        heartFull = new Image(getClass().getResourceAsStream("/Level/picture/HeartFull.png"));
        heartEmpty = new Image(getClass().getResourceAsStream("/Level/picture/HeartEmpty.png"));
    }

    // Method to update health display
    public void updatePlayerHeartView(int health) {
        currentHealth = Math.min(8, Math.max(0, health)); // Clamp between 0-8

        for (int i = 0; i < 8; i++) {
            if (i < currentHealth) {
                hearts[i].setImage(heartFull);  // Lighted icon
            } else {
                hearts[i].setImage(heartEmpty); // Dark icon
            }
        }
    }

}
