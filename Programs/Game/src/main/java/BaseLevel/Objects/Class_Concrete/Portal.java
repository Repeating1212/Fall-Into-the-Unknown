package BaseLevel.Objects.Class_Concrete;

import BaseLevel.Objects.Class_Base.ImageObject;
import Data.Loader.ImageLoader;
import BaseLevel.Objects.Supplier.PortalSupplier;
import Lvl_Sample.Managers.Observer;

public class Portal extends ImageObject {

    public Portal(Observer observer){
        super(PortalSupplier.getProperty(),
                ImageLoader.PORTAL,
                observer);
    }

    public void updateHealth(){}


}
