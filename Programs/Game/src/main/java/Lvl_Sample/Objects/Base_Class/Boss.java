package Lvl_Sample.Objects.Base_Class;

import javafx.scene.image.Image;
import Lvl_Sample.Data.Properties.Property;
import Lvl_Sample.Managers.Observer;
import Lvl_Sample.View.AttackVisualize.AttackVisual;

public abstract class Boss extends ImageObject {

    protected boolean isDefeated = false;
    protected GameObject enemy;

    public Boss(Property property, Image image, GameObject enemy, Observer observer){
        super(property, image, observer);
        this.enemy = enemy;
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

    public void getEnemy(GameObject enemy){
        this.enemy = enemy;
    }

    // Abstract class

    public abstract AttackVisual getAttackVisual();
}
