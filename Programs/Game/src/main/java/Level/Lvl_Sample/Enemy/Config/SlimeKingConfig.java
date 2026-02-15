package Level.Lvl_Sample.Enemy.Config;

import Level.BaseLevel.Objects.Class_Base.AttackArea;
import Level.BaseLevel.Properties.*;
import Level.BaseLevel.Skills.ConeAttackArea;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Level.BaseLevel.View.AttackVisualize.ConeAttackVisual;
import Level.Lvl_Sample.Behaviour.GroundSlap;
import Level.Lvl_Sample.Behaviour.Rush;
import Level.Lvl_Sample.Behaviour.Tackle;

public class SlimeKingConfig {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 150;
    public static final int SPEED = 2;
    public static final int INITIAL_X = 0;
    public static final int INITIAL_Y = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 75;

    public static final double RUSH_DAMAGE = 2;
    public static final double ATTACK_RIGID = 2.5;
    public static final double DEAD_DURATION = 1.5;

    // Rush
    public static final double RUSH_COOLDOWN = 5;
    public static final double RUSH_RANGE = 500;
    public static final double RUSH_SPEED_MULTIPLY = 3.0;
    public static final double RUSH_RIGID = 2.0;

    // AttackConfig Component
    public static final double DAMAGE = 4;
    public static final double ATTACK_RANGE = 500;
    public static final double ATTACK_AREA_ANGLE = 135;
    public static final double ATTACK_COOLDOWN = 3.0;
    // AttackConfig Behaviour
    public static final double ATTACK_DISTANCE_OFFSET = ATTACK_RANGE * 0.8;
    // AttackConfig Animation
    public static final double FADE_OUT_DURATION = 0.6;
    public static final double ENLARGE_DURATION = 3.0;
    public static final double TOTAL_ANIMATION_PERIOD = (FADE_OUT_DURATION + ENLARGE_DURATION);
    public static final double ATTACK_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 1.2;

    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position = new Position(INITIAL_X, INITIAL_Y);
        HitBox hitBox = new HitBox(WIDTH, HEIGHT, position, SlimeKingConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SlimeKingConfig.SPEED, status);
        Health health = new Health(SlimeKingConfig.MAXIMUM_HEALTH,status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Rush getRush(Property owner){
        return new Rush(RUSH_RANGE, RUSH_COOLDOWN, DAMAGE, RUSH_SPEED_MULTIPLY, owner, RUSH_RIGID);
    }

    public static GroundSlap getGroupSlap(Property owner){
        ConeAttackVisual attackVisual = new ConeAttackVisual(ATTACK_RANGE, ATTACK_AREA_ANGLE, ENLARGE_DURATION, FADE_OUT_DURATION);
        ConeAttackArea attackArea = new ConeAttackArea(ATTACK_RANGE, ATTACK_AREA_ANGLE);

        return new GroundSlap(attackVisual, ATTACK_RIGID_TIME, attackArea, DAMAGE, ATTACK_COOLDOWN, ENLARGE_DURATION, TOTAL_ANIMATION_PERIOD, ATTACK_DISTANCE_OFFSET, owner);
    }

    public static Tackle getTackle(Property owner, int size){
        return new Tackle(size, ATTACK_RIGID, owner);
    }
}
