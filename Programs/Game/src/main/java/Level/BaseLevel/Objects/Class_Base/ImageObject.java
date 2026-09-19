package Level.BaseLevel.Objects.Class_Base;

import Level.BaseLevel.Manager.Observer;
import Data.DataClass.DeadAnimation;
import Level.BaseLevel.Properties.Position;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Level.BaseLevel.Properties.Property;

public abstract class ImageObject extends GameObject {
    protected ImageView sprite = new ImageView();
    protected DeadAnimation deadAnimation;
    private boolean pointLeft = true;
    private boolean pointRight = true;

    public ImageObject(Property property, Image image, double deadDuration){
        super(property);
        this.property = property;

        createSprite(image);
        updateSpritePosition();
        this.deadAnimation = new DeadAnimation(sprite, deadDuration);
    }

    protected void updateSpritePosition() {
        if (pointLeft) sprite.setScaleX(1);
        if (pointRight) sprite.setScaleX(-1);
        sprite.setX(property.getX());
        sprite.setY(property.getY());
        property.resetDebugHitBox();
    }

    @Override
    public void update(double deltaTime, Observer observer){
        handleDeadAnimation();
        if ( property.isAlive() ) {
            // Prevent dead changing direction
            pointLeft = property.isMovingLeft();
            pointRight = property.isMovingRight();
        }
        updateSpritePosition();
        super.update(deltaTime, observer);
    }

    @Override
    public boolean removeCondition(){
        return deadAnimation.isEnd();
    }

    @Override
    public Node getSprite(){
        return sprite;
    }

    @Override
    public void setPosition(Position position) {
        super.setPosition(position);
        updateSpritePosition();
    }

    // Private Method

    private void createSprite(Image image) {
        if (image == null) return;
        sprite = new ImageView(image);
        sprite.setFitWidth(property.getWidth());
        sprite.setFitHeight(property.getHeight());
        updateSpritePosition();
    }

    protected void resetSprite(){
        sprite.setFitWidth(property.getWidth());
        sprite.setFitHeight(property.getHeight());
    }

    private void handleDeadAnimation(){
        if (property.isDead()){
            deadAnimation.play();
        }
    }
}
