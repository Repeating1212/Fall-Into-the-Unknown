package Level.Objects.Concrete_Class;

import Game_Data.Config.ImageConfig;
import Level.Managers.Observer;
import Level.Objects.Base_Class.ImageObject;
import Level.Data.Config.StakeConfig;
import Level.Data.Suppliers.StakeSupplier;


public class Stake extends ImageObject {

    private boolean isDefeated = false;

    // Constructor
    public Stake(Observer observer) {
        super( StakeSupplier.getProperty(), ImageConfig.STAKE, observer);
    }

    @Override
    protected void updateHealth(){
        observer.updateBossHealthBar(property.getHealthPercentage());
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
}
