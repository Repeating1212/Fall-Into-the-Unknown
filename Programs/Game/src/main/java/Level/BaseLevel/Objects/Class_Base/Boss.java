package Level.BaseLevel.Objects.Class_Base;

import javafx.scene.image.Image;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Managers.Observer;

public abstract class Boss extends ImageObject {

    protected boolean isDefeated = false;

    public Boss(Property property, Image image, Observer observer){
        super(property, image, observer);
    }

    public void defeat() {
        isDefeated = true;
        if (sprite != null) {
            sprite.setVisible(false);
        }
    }

    public boolean isDefeated() {
        return isDefeated;
    }

    @Override
    protected void updateHealth(){
        observer.updateBossHealthBar(property.getHealthPercentage());
    }

    public boolean checkAlive(){
        if (property.getCurrentHealth() == 0 && (! isDefeated())){
            defeat();
            return true;
        }
        return false;
    }
}
