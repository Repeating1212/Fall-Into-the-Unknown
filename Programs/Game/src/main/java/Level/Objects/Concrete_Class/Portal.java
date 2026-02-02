package Level.Objects.Concrete_Class;

import Game_Data.Config.ImageConfig;
import Level.Data.Config.PortalConfig;
import Level.Data.Suppliers.PortalSupplier;
import Level.Managers.Observer;
import Level.Objects.Base_Class.ImageObject;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                ImageConfig.PORTAL,
                observer);
    }

    public void updateHealth(){}


}
