package Level.Lvl_Sample.Behaviour;

import Data.DataClass.Timer;
import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class DeadAnimation {

    private final ParallelTransition animation;
    private boolean isActivated = false;

    public DeadAnimation(ImageView imageView, double totalTimeSeconds) {

        // Fade out
        FadeTransition fade = new FadeTransition(Duration.seconds(totalTimeSeconds * 0.4), imageView);
        fade.setFromValue(1.0);
        fade.setToValue(0.0);

        // Scale down (shrink)
        ScaleTransition scale = new ScaleTransition(Duration.seconds(totalTimeSeconds * 0.4), imageView);
        scale.setFromX(1.0);
        scale.setFromY(1.0);
        scale.setToX(0.0);
        scale.setToY(0.0);

        // Play animations together for first part
        animation = new ParallelTransition(fade, scale);
    }

    public void play() {
        if (isActivated) return;
        animation.play();
        isActivated = true;
    }

    public boolean isEnd() {
        return isActivated &&
                animation.getStatus() == Animation.Status.STOPPED;
    }
}