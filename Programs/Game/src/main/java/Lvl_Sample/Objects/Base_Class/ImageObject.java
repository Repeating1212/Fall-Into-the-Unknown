package Lvl_Sample.Objects.Base_Class;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Lvl_Sample.Data.Properties.Property;
import Lvl_Sample.Managers.Observer;

public abstract class ImageObject extends GameObject {
    protected ImageView sprite = new ImageView();

    public ImageObject(Property property, Image image, Observer observer){
        super(property, observer);
        this.property = property;
        createSprite(image);
        updateSpritePosition();
    }

    protected void updateSpritePosition() {
        sprite.setX(property.getX());
        sprite.setY(property.getY());
        property.resetDebugHitBox();
    }

    // Private Method
    private void createSprite(Image image) {
        if (image == null) return;
        sprite = new ImageView(image);
        sprite.setFitWidth(property.getWidth());
        sprite.setFitHeight(property.getHeight());
        updateSpritePosition();
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
}
