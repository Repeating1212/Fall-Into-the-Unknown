package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.Lvl_Sample.Enemy.Config.StakeConfig;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;

import java.util.ArrayList;


public class Stake extends ImageObject {

    private boolean isDefeated = false;

    // Constructor
    public Stake() {
        super( StakeConfig.getProperty(), ImageLoader.STAKE, StakeConfig.DEAD_DURATION);
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

    @Override
    public ArrayList<DisplayableObject> getRelatedSprite(){
        // Null
        return new ArrayList<>();
    }
}
