package Level.Lvl_Sample.Behaviour;

import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Data.DataClass.Timer;
import Level.BaseLevel.Manager.Observer;

import java.util.ArrayList;

public class GroundSlap implements Behaviour{

    private final double ATTACK_DAMAGE;
    private final double ATTACK_OFFSET;
    private final double RIGID_DURATION;

    private final AttackVisual attackVisual;
    private final AttackArea attackArea;
    private final Timer cooldown;
    private final Timer enlargeTimer;
    private final Timer animationTimer;
//    private final Timer rigidTimer;
    private Property owner;
    private boolean autoActivate = true;


    public GroundSlap(AttackVisual attackVisual, double attackRigidTime, AttackArea attackArea, double attackDamage,
                      double cooldown, double enlargeTime, double animationPeriod, double attackOffset, Property owner) {
        this.attackVisual = attackVisual;
        this.attackArea = attackArea;
        this.ATTACK_DAMAGE = attackDamage;
        this.ATTACK_OFFSET = attackOffset;
        this.RIGID_DURATION = attackRigidTime;

        this.cooldown = new Timer(cooldown);
        this.enlargeTimer = new Timer(enlargeTime);
        this.animationTimer = new Timer(animationPeriod);
        this.owner = owner;
    }

    @Override
    public void update(double deltaTime, Observer observer) {
        enlargeTimer.update(deltaTime);
        cooldown.update(deltaTime);
        animationTimer.update(deltaTime);
        attackVisual.update(deltaTime);

        if (autoActivate) activate(observer);
        handlePendingAttack(observer.getPlayer().getCenterPos());
        handleDamaging(observer.getHealthObj());
    }


    @Override
    public ArrayList<DisplayableObject> getVisual(){
        ArrayList<DisplayableObject> temp = new ArrayList<>();
        temp.add(attackVisual);
        return temp;
    }

    @Override
    public boolean isRunning(){
        return animationTimer.isTicking();
    }

    @Override
    public void activate(Observer observer){
        if (cooldown.isDeactive() && enlargeTimer.isDeactive() &&
                isNearEnemy(observer.getPlayer())) {
            enlargeTimer.setPending();
            animationTimer.start();
            owner.pauseMovement(RIGID_DURATION);
        }
    }

    @Override
    public boolean isCooldown(){
        return cooldown.isTicking();
    }

    @Override
    public void setAutoActivate(boolean autoActivate){
        this.autoActivate = autoActivate;
    }

    // Private Method

    private void handlePendingAttack(Position destinationPos) {
        if (enlargeTimer.isPending()) {
            enlargeTimer.start();
            cooldown.setPending();
            attackVisual.activate(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
            attackArea.updatePosition(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }

    private void handleDamaging(ArrayList<GameObject> healthObj) {
        if (enlargeTimer.isEnd() && cooldown.isPending()) {
            cooldown.start();
            for (GameObject entity : healthObj) {
                if (entity.getProperty() == owner) continue;
                if (attackArea.isHitBoxInArea(entity.getProperty())) {
                    entity.takeDamage(ATTACK_DAMAGE);
                }
            }
        }
    }

    private boolean isNearEnemy(GameObject enemy){
        double horizontalDistance = Math.abs(owner.getX() - enemy.getX());
            double verticalDistance = Math.abs(owner.getY() - enemy.getY());
        return ( horizontalDistance < ATTACK_OFFSET &&
                verticalDistance < ATTACK_OFFSET);
    }
}
