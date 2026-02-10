package BaseLevel.Objects.Class_Concrete;

import BaseLevel.Objects.Class_Base.ImageObject;
import BaseLevel.Properties.Position;
import BaseLevel.Properties.Property;
import Data.Loader.ImageLoader;
import BaseLevel.Objects.Supplier.PortalSupplier;
import Lvl_Sample.Managers.Observer;

public class Portal extends ImageObject {

    public Portal(Observer observer, Property property){
        super(PortalSupplier.getProperty(property),
                ImageLoader.PORTAL,
                observer);
    }

    public void updateHealth(){}


}
