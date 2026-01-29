package Level.View;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;

public class BossHealthBar {
    @FXML
    private final ProgressBar bossHealthBar;

    public BossHealthBar (ProgressBar bossHealthBar){
        this.bossHealthBar = bossHealthBar;
        bossHealthBar.setProgress(1.0);
    }

    public void updateHealthBar(double progress) {
        bossHealthBar.setProgress(progress);

        // Change color based on health
        if (progress > 0.6) {
            bossHealthBar.setStyle("-fx-accent: grey;");
        } else if (progress > 0.3) {
            bossHealthBar.setStyle("-fx-accent: grey;");
        } else {
            bossHealthBar.setStyle("-fx-accent: grey;");
        }
    }
}
