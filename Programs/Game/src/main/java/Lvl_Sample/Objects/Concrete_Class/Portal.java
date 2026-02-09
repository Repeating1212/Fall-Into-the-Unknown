package Lvl_Sample.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Lvl_Sample.Data.Suppliers.PortalSupplier;
import Lvl_Sample.Managers.Observer;
import Lvl_Sample.Objects.Base_Class.ImageObject;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                ImageLoader.PORTAL,
                observer);
    }

    public void updateHealth(){}


}
