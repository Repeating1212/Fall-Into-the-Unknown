package Level.Lvl_Sample.Behaviour;

import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Data.DataClass.Timer;
import Level.Lvl_Sample.Managers.Observer;

import java.util.ArrayList;

public class GroundSlap {

    private final double ATTACK_DAMAGE;
    private final double ATTACK_RIGID_TIME;

    private final AttackVisual attackVisual;
    private final AttackArea attackArea;
    private final Timer cooldown;
    private final Timer enlargeTimer;
    private final Timer animationTimer;
    private Property owner;

    // private value
    private Position destinationPos = new Position();
    private ArrayList<GameObject> targetEntity = new ArrayList<GameObject>();


    public GroundSlap(AttackVisual attackVisual, double attackRigidTime, AttackArea attackArea, double attackDamage,
                      double cooldown, double enlargeTime, double animationPeriod) {
        this.attackVisual = attackVisual;
        this.attackArea = attackArea;
        this.ATTACK_RIGID_TIME = attackRigidTime;
        this.ATTACK_DAMAGE = attackDamage;

        this.cooldown = new Timer(cooldown);
        this.enlargeTimer = new Timer(enlargeTime);
        this.animationTimer = new Timer(animationPeriod);
    }

    public void initializeData(Property owner) {
        this.owner = owner;
    }

    public void activate(double positionX, double positionY) {
        activate(new Position(positionX, positionY));
    }

    public void activate(Position destinationPos) {
        if (cooldown.isDeactive() && enlargeTimer.isDeactive()) {
            this.destinationPos = destinationPos;
            enlargeTimer.setPending();
            animationTimer.setPending();
        }
    }

    public void update(double deltaTime, Observer observer) {
        this.targetEntity = getTargets(observer.getHealthObj());

        enlargeTimer.update(deltaTime);
        cooldown.update(deltaTime);
        animationTimer.update(deltaTime);
        attackVisual.update(deltaTime);

        handlePendingAttack();
        handleDamaging();
        handleAnimation(observer);
    }

    public void handleRigid() {
        if (enlargeTimer.isPending()) {
            owner.pauseMovement(ATTACK_RIGID_TIME);
        }
    }

    public AttackVisual getAttackVisual(){
        return attackVisual;
    }

    // Private Method

    private void handlePendingAttack() {
        if (enlargeTimer.isPending()) {
            enlargeTimer.start();
            cooldown.setPending();
            attackVisual.activate(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
            attackArea.updatePosition(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }

    private void handleDamaging() {
        if (enlargeTimer.isEnd() && cooldown.isPending()) {
            attack(targetEntity);
            cooldown.start();
        }
    }

    private ArrayList<GameObject> getTargets(ArrayList<GameObject> healthObj) {
        ArrayList<GameObject> targets = new ArrayList<>();
        for (GameObject entity : healthObj) {
            if (entity.getProperty() == owner) continue;
            targets.add(entity);
        }
        return targets;
    }

    private void attack(ArrayList<GameObject> targets) {
        for (GameObject entity : targets) {
            if (attackArea.isHitBoxInArea(entity.getProperty())) {
                entity.takeDamage(ATTACK_DAMAGE);
            }
        }
    }

    private void handleAnimation(Observer observer){
        if (animationTimer.isPending()) {
            animationTimer.start();
            attackVisual.activate(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }
}
