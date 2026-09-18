package Level.Lvl_1.Enemy.Config;

import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Level.BaseLevel.Skills.ConeAttackArea;
import Level.BaseLevel.Properties.*;
import Level.BaseLevel.View.AttackVisualize.ConeAttackVisual;
import Level.Lvl_1.Behaviour.GroundSlap;

public class GuardConfig {
    public static final int HEIGHT = 50;
    public static final int WIDTH = 50;
    public static final int SPEED = 4;
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 20;

    // AttackConfig Component
    public static final double DAMAGE = 4;
    public static final double ATTACK_RANGE = 200;
    public static final double ATTACK_AREA_ANGLE = 135;
    public static final double ATTACK_COOLDOWN = 3.0;
    // AttackConfig Behaviour
    public static final double ATTACK_DISTANCE_OFFSET = ATTACK_RANGE * 0.8;
    // AttackConfig Animation
    public static final double FADE_OUT_DURATION = 0.3;
    public static final double ENLARGE_DURATION = 1.5;
    public static final double TOTAL_ANIMATION_PERIOD = (FADE_OUT_DURATION + ENLARGE_DURATION);
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 1.2;

    public static final double DEAD_DURATION = 2.0;


    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(GuardConfig.INITIAL_X, GuardConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(GuardConfig.WIDTH, GuardConfig.HEIGHT, position, GuardConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(GuardConfig.SPEED, status);
        Health health = new Health(GuardConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static GroundSlap getGroundSlap(Property owner){
        AttackArea attackArea = new ConeAttackArea(GuardConfig.ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE);
        ConeAttackVisual attackVisual = new ConeAttackVisual(GuardConfig.ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE, GuardConfig.ENLARGE_DURATION, GuardConfig.FADE_OUT_DURATION);
        return new GroundSlap(attackVisual, GuardConfig.ATTACK_RIGID_TIME, attackArea, GuardConfig.DAMAGE, GuardConfig.ATTACK_COOLDOWN, GuardConfig.ENLARGE_DURATION, GuardConfig.TOTAL_ANIMATION_PERIOD, ATTACK_DISTANCE_OFFSET, owner);
    }
}
