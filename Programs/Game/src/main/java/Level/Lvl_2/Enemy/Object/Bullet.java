package Level.Lvl_2.Enemy.Object;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Level.Lvl_2.Enemy.Behaviour.BulletBehaviour;
import Level.Lvl_2.Enemy.Config.BulletConfig;
import Level.BaseLevel.Properties.Position;


public class Bullet extends ImageObject {

    private boolean removeCondition = false;
    private BulletBehaviour bulletBehaviour;

    public Bullet(Position position){
        super(BulletConfig.getProperty(), ImageLoader.L02_BULLET, BulletConfig.DEAD_DURATION);
        property.pointTo(position);
        bulletBehaviour = new BulletBehaviour(1.0, this);
    }


    @Override
    public void update(double deltaTime, Observer observer) {
        if (property.isTouchBoundary()){
            removeCondition = true;
        }
        super.update(deltaTime, observer);
        if (! removeCondition){
            property.updateStatus(deltaTime);
            property.move(observer.getGameObjPrt(), deltaTime);
            bulletBehaviour.update(deltaTime, observer);
        }
    }

    @Override
    public boolean removeCondition() {
        return removeCondition;
    }

    // Setter

    public void setDisappear(){
        removeCondition = true;
    }
}
