package Level.Objects.Concrete_Class;

import Data.Supplier.ImageLoader;
import Level.Managers.Observer;
import Level.Objects.Base_Class.ImageObject;
import Level.Data.Suppliers.StakeSupplier;


public class Stake extends ImageObject {

    private boolean isDefeated = false;

    // Constructor
    public Stake(Observer observer) {
        super( StakeSupplier.getProperty(), ImageLoader.STAKE, observer);
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
