package Level.Lvl_Sample.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_Sample.Behaviour.Behaviour;
import Level.Lvl_Sample.Behaviour.BossBehaviour;
import Level.Lvl_Sample.Enemy.Config.SlimeKingConfig;
import Level.Lvl_Sample.Enemy.Interface.Enemy;

import java.util.ArrayList;
import java.util.Properties;

public class SlimeKing extends Enemy {

    public SlimeKing(ArrayList<Property> properties){
        super(SlimeKingConfig.getProperty(), ImageLoader.L01_SLIME_KING, SlimeKingConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                new BossBehaviour(property)
        };
    }
}
