package Level.BaseLevel.Objects.Class_Base;

import Level.BaseLevel.Manager.Observer;
import Level.Lvl_Sample.Behaviour.DeadAnimation;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Level.BaseLevel.Properties.Property;

public abstract class ImageObject extends GameObject {
    protected ImageView sprite = new ImageView();
    protected DeadAnimation deadAnimation;

    public ImageObject(Property property, Image image, double deadDuration){
        super(property);
        this.property = property;

        createSprite(image);
        updateSpritePosition();
        this.deadAnimation = new DeadAnimation(sprite, deadDuration);
    }

    protected void updateSpritePosition() {
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);

        sprite.setX(property.getX());
        sprite.setY(property.getY());
        property.resetDebugHitBox();
    }

    @Override
    public void update(double deltaTime, Observer observer){
        handleDeadAnimation();
        super.update(deltaTime, observer);
        if ( property.isAlive() ) {
            updateSpritePosition();
        }
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
    public void setPosition(double x, double y) {
        super.setPosition(x,y);
        updateSpritePosition();
    }

    @Override
    protected void checkBoundaries(double minX, double minY, double maxX, double maxY) {
        super.checkBoundaries(minX, minY, maxX, maxY);
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

    private void handleDeadAnimation(){
        if (property.isDead()){
            deadAnimation.play();
        }
    }
}
