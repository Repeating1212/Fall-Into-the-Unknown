package Level.BaseLevel.View.AttackVisualize;

import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import Data.DataClass.Vector2D;
import Level.BaseLevel.Properties.Position;

import java.util.ArrayList;

public class ConeAttackVisual extends AttackVisual {
    private final Arc arc = new Arc();
    private final double ARC_RADIUS;
    private final double ARC_ANGLE;
    private final double ENLARGE_DURATION;
    private final double FADE_OUT_DURATION;

    private double elapsedTime = 0;
    private double currentRadius = 0;
    private boolean isActive = false;

    public ConeAttackVisual(double radius, double angle, double enlargeDuration, double fadeOutDuration) {
        this.ARC_RADIUS = radius;
        this.ARC_ANGLE = angle;
        this.ENLARGE_DURATION = enlargeDuration;
        this.FADE_OUT_DURATION = fadeOutDuration;
        initializeArc();
    }

    @Override
    public Node getSprite() { return arc; }

    public void activate(Position startPos, Position endPos) {
        // Position the arc center
        arc.setCenterX(startPos.getX());
        arc.setCenterY(startPos.getY());
        arc.setStartAngle(calculateAngle(startPos, endPos));
        arc.setRadiusX(0);
        arc.setRadiusY(0);

        // Reset state
        isActive = true;
        elapsedTime = 0;
    }

    public void update(double deltaTime) {
        if (!isActive) return;

        elapsedTime += deltaTime;
        double progress = elapsedTime / ENLARGE_DURATION;

        if (elapsedTime < ENLARGE_DURATION) {
            // Expand radius
            currentRadius = progress * ARC_RADIUS;
            arc.setRadiusX(currentRadius);
            arc.setRadiusY(currentRadius);
            arc.setOpacity(1.0);
        }
        else if(elapsedTime - ENLARGE_DURATION < FADE_OUT_DURATION){
            // Fade out
            arc.setRadiusX(ARC_RADIUS);
            arc.setRadiusY(ARC_RADIUS);
            double fadeProgress = ((elapsedTime - ENLARGE_DURATION) / FADE_OUT_DURATION);
            double opacity = 1.0 - fadeProgress;
            arc.setOpacity(opacity);

            // Pulsing effect
            double pulseScale = 1.0 + 0.05 * Math.sin(fadeProgress * Math.PI * 4);
            arc.setScaleX(pulseScale);
            arc.setScaleY(pulseScale);
        }
        else {
            arc.setOpacity(1.0);
            isActive = false;
        }
    }

    public boolean isActive(){
        return isActive;
    }

    // Private Method

    private void initializeArc() {
        arc.setLength(ARC_ANGLE);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 0, 0, 0.3));
        arc.setStroke(Color.RED);
        arc.setStrokeWidth(2);
    }

    private double calculateAngle(Position currentPos, Position destinationPos){
        Vector2D attackVector = Vector2D.getAttackVector(currentPos, destinationPos);
        Vector2D temp = attackVector.normalize();
        double dirAngle = temp.getAngleDegrees();
        return (- dirAngle - arc.getLength()/2); // dirAngle is opposite
    }
}