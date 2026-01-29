package Level.Objects.Concrete_Class;

import Level.Data.Config.PortalConfig;
import Level.Data.Suppliers.PortalSupplier;
import Level.Managers.Observer;
import Level.Objects.Base_Class.ImageObject;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                PortalConfig.loadImage(),
                observer);
    }

    public void updateHealth(){}


}
