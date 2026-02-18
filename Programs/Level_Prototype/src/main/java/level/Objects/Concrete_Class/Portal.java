package level.Objects.Concrete_Class;

import level.Data.Config.PortalConfig;
import level.Data.Suppliers.PortalSupplier;
import level.Managers.Observer;
import level.Objects.Base_Class.ImageObject;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                PortalConfig.loadImage(),
                observer);
    }

    public void updateHealth(){}


}
