package Level.BaseLevel.Skills;

import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Data.DataClass.Timer;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Properties.PlayerState;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Objects.Class_Base.GameObject;
import Level.BaseLevel.Properties.Property;
import Level.BaseLevel.Properties.Position;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;

import java.util.ArrayList;

public class Attack implements Skill {

    private final double DAMAGE;
    private final double RIGID_DURATION;

    private final AttackVisual attackVisual;
    private final AttackArea attackArea;
    private final Timer cooldown;
    private final Timer enlargeTimer;
    private final Timer animationTimer;
    private Property owner;

    // private value
    private Position destinationPos = new Position();


    public Attack(AttackVisual attackVisual, double attackRigidTime, AttackArea attackArea, double attackDamage,
                  double cooldown, double enlargeTime, double animationPeriod){
        this.attackVisual = attackVisual;
        this.attackArea = attackArea;
        this.DAMAGE = attackDamage;
        this.RIGID_DURATION = attackRigidTime;

        this.cooldown = new Timer(cooldown);
        this.enlargeTimer = new Timer(enlargeTime);
        this.animationTimer = new Timer(animationPeriod);
    }

    public void initializeData(Property owner, PlayerState playerState){
        this.owner = owner;
    }

    public void activate(double positionX, double positionY){
        activate(new Position(positionX, positionY));
    }

    public void activate(Position destinationPos){
        if (cooldown.isDeactive() && enlargeTimer.isDeactive()) {
            this.destinationPos = destinationPos;
            enlargeTimer.setPending();
            animationTimer.setPending();
        }
    }

    public void update(double deltaTime, Observer observer){
        enlargeTimer.update(deltaTime);
        cooldown.update(deltaTime);
        animationTimer.update(deltaTime);
        attackVisual.update(deltaTime);

        handlePendingAttack();
        handleDamaging(observer.getHealthObj());
        handleAnimation();
    }

    public void handleRigid(){}

    public boolean isRunning(){
        return animationTimer.isTicking();
    }

    // Passing Method

    public DisplayableObject getVisual(){
        return attackVisual;
    }

    public double getCooldownPercentage() {return cooldown.getCooldownPercentage();}

    // Private Method

    private void handlePendingAttack(){
        if (enlargeTimer.isPending()){
            enlargeTimer.start();
            cooldown.setPending();
            owner.pauseMovement(RIGID_DURATION);
            attackArea.updatePosition(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }

    private void handleDamaging(ArrayList<GameObject> healthObj){
        if(enlargeTimer.isEnd() && cooldown.isPending()){
            for (GameObject entity : healthObj) {
                if (entity.getProperty() == owner) continue;
                if (attackArea.isHitBoxInArea(entity.getProperty())) {
                    entity.takeDamage(DAMAGE);
                }
            }
            cooldown.start();
        }
    }

    private void handleAnimation(){
        if (animationTimer.isPending()){
            animationTimer.start();
            attackVisual.activate(new Position(owner.getCenterX(), owner.getCenterY()), destinationPos);
        }
    }
}
