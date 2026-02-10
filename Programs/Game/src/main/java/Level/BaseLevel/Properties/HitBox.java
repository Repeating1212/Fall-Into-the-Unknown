package Level.BaseLevel.Properties;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class HitBox {

    private final int width;
    private final int height;
    private final Position position;
    private final boolean isBlockMovement; // True when object unable to pass through, wall etc
    private final double TOUCH_TOLERANCE = 3;

    private Rectangle debugView;

    public HitBox(int width, int height, Position position, Boolean isBlockMovement){
        this.height = height;
        this.width = width;
        this.position = position;
        this.isBlockMovement = isBlockMovement;
        createDebugView();
    }

    protected void resetBugView(){
        debugView.setX(position.getX());
        debugView.setY(position.getY());
    }

    // Toggle debug visualization
    protected void showDebug(boolean show) {
        if (debugView != null) {
            debugView.setVisible(show);
        }
    }

    // Get debug rectangle for adding to scene
    protected Rectangle getDebugHitBox() {
        return debugView;
    }

    protected boolean isCollide (HitBox other) {
        return position.getX() < other.position.getX() + other.getWidth() &&
                position.getX() + width > other.position.getX() &&
                position.getY() < other.position.getY() + other.getHeight() &&
                position.getY() + height > other.position.getY();
    }

    protected boolean isTouch(HitBox other) {
        return  -TOUCH_TOLERANCE + position.getX() < other.position.getX() + other.getWidth() &&
                TOUCH_TOLERANCE + position.getX() + width > other.position.getX() &&
                -TOUCH_TOLERANCE + position.getY() < other.position.getY() + other.getHeight() &&
                TOUCH_TOLERANCE + position.getY() + height > other.position.getY();
    }

    protected boolean isBlocked(ArrayList<HitBox> others){
        if (others == null) return false;
        for (HitBox other : others){
            if (other == this )continue;
            if (! other.isBlockMovement()) continue;
            if (isCollide(other)) return true;
        }
        return false;
    }

    protected boolean isCollide (ArrayList<HitBox> others){
        if (others == null) return false;
        for (HitBox other : others){
            if (other == this )continue;
            if (isCollide(other)) return true;
        }
        return false;
    }

    protected Boolean isBlockMovement(){
        return isBlockMovement;
    }

    // Getter Method

    protected int getWidth() { return width; }

    protected int getHeight() { return height; }

    protected Position getPosition() {
        return position;
    }

    protected double getCenterX() { return position.getX() + width / 2.0;}

    protected double getCenterY() { return position.getY() + height / 2.0; }

    protected Position getCenterPos() { return  new Position(getCenterX(), getCenterY());}

    protected Position getTopLeft() {
        return new Position(position.getX(), position.getY());
    }

    protected Position getTopRight() {
        return new Position(position.getX() + width, position.getY());
    }

    protected Position getBottomLeft() {
        return new Position(position.getX(), position.getY() + height);
    }

    protected Position getBottomRight() {
        return new Position(position.getX() + width, position.getY() + height);
    }

    // Private Method

    private void createDebugView() {
        debugView = new Rectangle(width, height);
        debugView.setX(position.getX());
        debugView.setY(position.getY());
        debugView.setFill(Color.TRANSPARENT);
        debugView.setStroke(Color.RED);
        debugView.setStrokeWidth(1);
        debugView.setOpacity(0.7);
        debugView.setVisible(false); // Hidden by default
    }
}
