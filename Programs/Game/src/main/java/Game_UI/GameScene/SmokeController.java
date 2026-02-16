package Game_UI.GameScene;

import Data.Interface.OverlayController;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.awt.*;

public class SmokeController extends OverlayController {

    @FXML
    private ImageView image1, image2, image3, image4, image5, image6, image7, image8, image9;
    @FXML
    private Pane smokePane, background;

    @FXML
    public void initialize(){
        ImageView[] imageViews = new ImageView[]{image1, image2, image3, image4, image5, image6, image7, image8, image9};
        initializeAnimation(imageViews);
    }

    private void initializeAnimation(ImageView[]  imageViews) {
        TranslateTransition animation;
        int millisecond = 600;

        animation = new TranslateTransition(Duration.millis(millisecond), image1);
        animation.setToX(450);
        animation.setToY( - 450);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image2);
        animation.setToX(200);
        animation.setToY(200);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image3);
        animation.setToX(275);
        animation.setToY(275);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image4);
        animation.setToX(275);
        animation.setToY(275);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image5);
        animation.setToX(-500);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image6);
        animation.setToX(-550);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image7);
        animation.setToX(-725);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image8);
        animation.setToX(-725);
        animation.play();

        animation = new TranslateTransition(Duration.millis(millisecond), image9);
        animation.setToX(- 450);
        animation.setToY(- 450);
        animation.play();
        animation.setOnFinished(e -> {
            handleClose();
        });

        for (ImageView imageView: imageViews){
            FadeTransition fade = new FadeTransition(Duration.millis(millisecond), imageView);
            fade.setFromValue(0.9);
            fade.setToValue(0.3);
            fade.play();
        }

        FadeTransition fade = new FadeTransition(Duration.millis(millisecond), smokePane);
        fade.setFromValue(0.9);
        fade.setToValue(0.3);
        fade.play();

        FadeTransition backgroundFade = new FadeTransition(Duration.millis(millisecond), background);
        backgroundFade.setFromValue(1.0);
        backgroundFade.setToValue(0.0);
        backgroundFade.play();
    }

    private void handleClose() {
        if (rootPane == null) return;
        rootPane.getChildren().remove(smokePane);
    }
}
