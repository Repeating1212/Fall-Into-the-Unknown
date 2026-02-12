package Level.BaseLevel.Objects.Class_Concrete;

import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.BaseLevel.Objects.Config.PortalConfig;
import Level.BaseLevel.Properties.Property;
import Data.Loader.ImageLoader;
import Level.Lvl_Sample.Managers.Observer;

public class Portal extends ImageObject {

    public Portal(Observer observer, Property property){
        super(PortalConfig.getProperty(property),
                ImageLoader.PORTAL);
    }
}
