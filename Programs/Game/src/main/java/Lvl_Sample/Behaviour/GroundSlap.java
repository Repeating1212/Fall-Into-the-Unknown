package Lvl_Sample.Behaviour;

import BaseLevel.Objects.Class_Base.AttackArea;
import BaseLevel.Objects.Class_Base.GameObject;
import BaseLevel.Properties.PlayerState;
import BaseLevel.Properties.Position;
import BaseLevel.Properties.Property;
import BaseLevel.Skills.Skill;
import BaseLevel.View.AttackVisualize.AttackVisual;
import Data.DataClass.Timer;
import Lvl_Sample.Managers.Observer;

import java.util.ArrayList;

public class GroundSlap {

    private final double ATTACK_DAMAGE;
    private final double ATTACK_RIGID_TIME;

    private final AttackVisual ATTACK_VISUAL;
    private final AttackArea ATTACK_AREA;
    private final Timer COOLDOWN;
    private final Timer ANIMATION_TIMER;
    private Property owner;

    // private value
    private Position destinationPos = new Position();
    private ArrayList<GameObject> targetEntity = new ArrayList<GameObject>();


    public GroundSlap(AttackVisual attackVisual, double attackRigidTime, AttackArea attackArea, double attackDamage,
                      double cooldown, double enlargeTime) {
        this.ATTACK_VISUAL = attackVisual;
        this.ATTACK_AREA = attackArea;
        this.ATTACK_RIGID_TIME = attackRigidTime;
        this.ATTACK_DAMAGE = attackDamage;

        this.COOLDOWN = new Timer(cooldown);
        this.ANIMATION_TIMER = new Timer(enlargeTime);
    }

    public void initializeData(Property owner) {
        this.owner = owner;
    }

    public void activate(double positionX, double positionY) {
        activate(new Position(positionX, positionY));
    }

    public void activate(Position destinationPos) {
        if (COOLDOWN.isDeactive() && ANIMATION_TIMER.isDeactive()) {
            this.destinationPos = destinationPos;
            ANIMATION_TIMER.setPending();
        }
    }

    public void update(double deltaTime, Observer observer) {
        this.targetEntity = getTargets(observer.getLivingEntities());
        ANIMATION_TIMER.update(deltaTime);
        COOLDOWN.update(deltaTime);
        ATTACK_VISUAL.update(deltaTime);
        handlePendingAttack();
        handleDamaging();
    }

    public void handleRigid() {
        if (ANIMATION_TIMER.isPending()) {
            owner.pauseMovement(ATTACK_RIGID_TIME);
        }
    }

    // Passing Method

    public AttackVisual getAttackVisual() {
        return ATTACK_VISUAL;
    }

    public double getCooldownPercentage() {
        return COOLDOWN.getCooldownPercentage();
    }

    // Private Method
    private void handlePendingAttack() {
        if (ANIMATION_TIMER.isPending()) {
            ANIMATION_TIMER.start();
            COOLDOWN.setPending();
            ATTACK_VISUAL.activate(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
            ATTACK_AREA.updatePosition(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }

    private void handleDamaging() {
        if (ANIMATION_TIMER.isEnd() && COOLDOWN.isPending()) {
            attack(targetEntity);
            COOLDOWN.start();
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
            if (ATTACK_AREA.isHitBoxInArea(entity.getProperty())) {
                entity.takeDamage(ATTACK_DAMAGE);
            }
        }
    }
}
