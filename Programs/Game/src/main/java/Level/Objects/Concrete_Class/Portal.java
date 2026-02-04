package Level.Objects.Concrete_Class;

import Game_Data.Supplier.ImageLoader;
import Level.Data.Suppliers.PortalSupplier;
import Level.Managers.Observer;
import Level.Objects.Base_Class.ImageObject;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                ImageLoader.PORTAL,
                observer);
    }

    public void updateHealth(){}


}
