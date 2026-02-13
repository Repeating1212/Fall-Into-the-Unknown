package Level.BaseLevel.View.AttackVisualize;

import Data.DataClass.Vector2D;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.Position;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Rectangle;

public class RectangleAttackVisual implements DisplayableObject {
    private final Rectangle rectangle = new Rectangle();
    private final double WIDTH;
    private final double HEIGHT;
    private final double ENLARGE_DURATION;
    private final double FADE_OUT_DURATION;

    private double elapsedTime = 0;
    private boolean isActive = false;

    public RectangleAttackVisual(double width, double height, double enlargeDuration, double fadeOutDuration) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ENLARGE_DURATION = enlargeDuration;
        this.FADE_OUT_DURATION = fadeOutDuration;
        initializeArc();
    }

    @Override
    public Node getSprite() { return rectangle; }

    public void activate(Position startPos, double direction) {
        // Position the arc center
        rectangle.setX(startPos.getX() - WIDTH/2);
        rectangle.setY(startPos.getY());
        rectangle.setRotate(direction);
        System.out.println(direction);
//        calculation(direction);

        // Reset state
        isActive = true;
        elapsedTime = 0;

    }

    public void update(double deltaTime) {
        if (!isActive) return;

        elapsedTime += deltaTime;
        double progress = elapsedTime / ENLARGE_DURATION;

//        if (elapsedTime < ENLARGE_DURATION) {
//            rectangle.setOpacity(progress);
//        }
//        else if(elapsedTime - ENLARGE_DURATION < FADE_OUT_DURATION){
//            // Fade out
//            rectangle.setRadiusX(ARC_RADIUS);
//            rectangle.setRadiusY(ARC_RADIUS);
//            double fadeProgress = ((elapsedTime - ENLARGE_DURATION) / FADE_OUT_DURATION);
//            double opacity = 1.0 - fadeProgress;
//            rectangle.setOpacity(opacity);
//
//            // Pulsing effect
//            double pulseScale = 1.0 + 0.05 * Math.sin(fadeProgress * Math.PI * 4);
//            rectangle.setScaleX(pulseScale);
//            rectangle.setScaleY(pulseScale);
//        }
        if(elapsedTime > ENLARGE_DURATION) {
            rectangle.setOpacity(1.0);
            isActive = false;
        }
    }

    public boolean isActive(){
        return isActive;
    }

    // Private Method

    private void initializeArc() {
        rectangle.setHeight(HEIGHT);
        rectangle.setWidth(WIDTH);
        rectangle.setFill(Color.rgb(255, 0, 0, 0.3));
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(2);
    }

//    private double calculateAngle(Position currentPos, Position destinationPos){
//        Vector2D attackVector = Vector2D.getAttackVector(currentPos, destinationPos);
//        Vector2D temp = attackVector.normalize();
//        double dirAngle = temp.getAngleDegrees();
//        return (dirAngle - rectangle.getLength()/2);
//    }

    private void calculation(double direction){
        double centerX = rectangle.getX() + rectangle.getWidth() /2;
        double centerY = rectangle.getY() + rectangle.getHeight() /2;
        double angleRadians = Math.toRadians(direction);

        double newX = centerX * Math.cos(angleRadians) - centerY * Math.sin(angleRadians);
        double newY = centerX * Math.sin(angleRadians) + centerY * Math.cos(angleRadians);

        rectangle.setX(newX - rectangle.getWidth()/2);
        rectangle.setY(newY - rectangle.getHeight()/2);
    }
}