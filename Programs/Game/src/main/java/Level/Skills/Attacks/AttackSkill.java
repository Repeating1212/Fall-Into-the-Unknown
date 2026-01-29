package Level.Skills.Attacks;

import Level.Objects.Base_Class.GameObject;
import Level.Data.Properties.Property;
import Level.Data.Properties.Position;
import Level.Skills.Timer;
import Level.View.AttackVisualize.AttackVisual;

import java.util.ArrayList;

public class AttackSkill {
    private final AttackVisual ATTACK_VISUAL;
    private final AttackArea ATTACK_AREA;
    private final double ATTACK_DAMAGE;
    private final Property OWNER;
    private final double ATTACK_RIGID_TIME;
    private final Timer COOLDOWN;
    private final Timer ANIMATION_TIMER;

    // private value
//    private boolean pendingAttack = false;
    private Position destinationPos = new Position();
    private ArrayList<GameObject> targetEntity = new ArrayList<GameObject>();


    public AttackSkill(AttackVisual attackVisual, double attackRigidTime, Property owner, AttackArea attackArea, double attackDamage,
                       double cooldown, double animationTime){
        this.ATTACK_VISUAL = attackVisual;
        this.ATTACK_AREA = attackArea;
        this.ATTACK_RIGID_TIME = attackRigidTime;
        this.ATTACK_DAMAGE = attackDamage;
        this.OWNER = owner;

        this.COOLDOWN = new Timer(cooldown);
        this.ANIMATION_TIMER = new Timer(animationTime);

    }

    public void setAttack(double positionX, double positionY, ArrayList<GameObject> healthObj){
        setAttack(new Position(positionX, positionY), healthObj);
    }

    public void setAttack(Position destinationPos, ArrayList<GameObject> healthObj){
        if (COOLDOWN.isDeactive() && ANIMATION_TIMER.isDeactive()) {
            this.destinationPos = destinationPos;
            this.targetEntity = getTargets(healthObj);
            ANIMATION_TIMER.setPending();
        }
    }

    public void update(double deltaTime){
        ANIMATION_TIMER.update(deltaTime);
        COOLDOWN.update(deltaTime);
        ATTACK_VISUAL.update(deltaTime);
        handlePendingAttack();
        handleDamaging();
    }

    public void handleAttackRigid(Property owner){
        if (ANIMATION_TIMER.isPending()) {
            owner.pauseMovement(ATTACK_RIGID_TIME);
        }
    }

    // Passing Method

    public AttackVisual getAttackVisual(){
        return ATTACK_VISUAL;
    }

    public double getCooldownPercentage() {return COOLDOWN.getCooldownPercentage();}

    // Private Method
    private void handlePendingAttack(){
        if (ANIMATION_TIMER.isPending()){
            ANIMATION_TIMER.start();
            COOLDOWN.setPending();
            ATTACK_VISUAL.activate(new Position(OWNER.getCenterX(), OWNER.getCenterY()), destinationPos);
            ATTACK_AREA.updatePosition(new Position(OWNER.getCenterX(), OWNER.getCenterY()), destinationPos);
        }
    }

    private void handleDamaging(){
        if(ANIMATION_TIMER.isEnd() && COOLDOWN.isPending()){
            attack(targetEntity);
            COOLDOWN.start();
        }
    }

    private ArrayList<GameObject> getTargets(ArrayList<GameObject> healthObj){
        ArrayList<GameObject> targets = new ArrayList<>();
        for (GameObject entity : healthObj) {
                if (entity.getProperty() == OWNER) continue;
                targets.add(entity);
            }
        return targets;
    }

    private void attack(ArrayList<GameObject> targets){
        for (GameObject entity : targets) {
            if (ATTACK_AREA.isHitBoxInArea(entity.getProperty())) {
                entity.takeDamage(ATTACK_DAMAGE);
            }
        }
    }
}
