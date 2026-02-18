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

    public RectangleAttackVisual(double width, double height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        initializeArc();
    }

    @Override
    public Node getSprite() { return rectangle; }

    @Override
    public int getLayer(){
        return 1;
    }

    public void activate(Position startPos, double direction) {
        rectangle.setWidth(WIDTH);
        rectangle.setHeight(HEIGHT);

        // Calculate offset so that after rotation, left side touches startPos
        // For a rectangle rotated around its center, we need to offset by half width to the left
        double offsetX = -WIDTH / 2;  // Half width to the left

        // Convert offset based on rotation
        double radians = Math.toRadians(direction);
        double rotatedOffsetX = offsetX * Math.cos(radians);
        double rotatedOffsetY = offsetX * Math.sin(radians);

        // Position the rectangle so its center plus offset equals startPos
        rectangle.setX(startPos.getX() - rotatedOffsetX - WIDTH/2);
        rectangle.setY(startPos.getY() - rotatedOffsetY - HEIGHT/2);

        // Set rotation around center
        rectangle.setRotate(direction);

    }

    // Private Method

    private void initializeArc() {
        rectangle.setHeight(HEIGHT);
        rectangle.setWidth(WIDTH);
        rectangle.setFill(Color.rgb(255, 0, 0, 0.3));
        rectangle.setStroke(Color.RED);
        rectangle.setStrokeWidth(1);
    }
}