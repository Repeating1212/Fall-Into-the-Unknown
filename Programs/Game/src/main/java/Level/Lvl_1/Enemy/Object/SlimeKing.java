package Level.Lvl_1.Enemy.Object;

import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.Property;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Behaviour.BossBehaviour;
import Level.Lvl_1.Enemy.Config.SlimeKingConfig;
import Level.Lvl_1.Enemy.Interface.Slime;
import Level.Lvl_1.Enemy.Interface.Spawnable;

import java.util.ArrayList;
import java.util.Random;

public class SlimeKing extends Slime implements Spawnable {

    private boolean spawnable;

    public SlimeKing(ArrayList<Property> properties){
        super(1, SlimeKingConfig.getProperty(), ImageLoader.L01_SLIME_KING, SlimeKingConfig.DEAD_DURATION);
        property.spawnNearBoundary(properties);
    }

    @Override
    protected Behaviour[] getBehaviours(){
        return new Behaviour[]{
                new BossBehaviour(this)
        };
    }

    // Access by spawn behaviour
    public void setSpawnable(boolean spawnable){
        this.spawnable = spawnable;
    }

    // Slime Override

    @Override
    public boolean spawnable(){
        return spawnable;
    }

    @Override
    public void setSpawned(){
        spawnable = false;
    }

    @Override
    public ArrayList<Slime> getChildren(ArrayList<Property> properties){
        Random random = new Random();
        ArrayList<Slime> children = new ArrayList<>();

        int spawnNum = random.nextInt(SlimeKingConfig.MIN_SPAWN, SlimeKingConfig.MAX_SPAWN);
        for (int i = 0; i < spawnNum ; i ++){
            children.add(super.randomSpawn(properties,
                    property.duplicatePosition(),
                    SlimeKingConfig.CHILDREN_SIZE));
        }
        return children;
    }

}
