package Level.Lvl_1.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.BaseLevel.Skills.ConeAttackArea;
import Level.BaseLevel.View.AttackVisualize.ConeAttackVisual;
import Level.Lvl_1.Behaviour.GroundSlap;
import Level.Lvl_1.Behaviour.Rush;
import Level.Lvl_1.Behaviour.Spawn;
import Level.Lvl_1.Enemy.Interface.Spawnable;

public class SlimeKingConfig {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 150;
    public static final int SPEED = 100;
    public static final int INITIAL_X = 0;
    public static final int INITIAL_Y = 0;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 60;

    public static final double DEAD_DURATION = 1.5;

    // Rush
    public static final double RUSH_DAMAGE = 2;
    public static final double RUSH_COOLDOWN = 5;
    public static final double RUSH_RANGE = 500;
    public static final double RUSH_SPEED_MULTIPLY = 3.0;
    public static final double RUSH_RIGID = 2.0;

    // AttackConfig Component
    public static final double SLAP_DAMAGE = 4;
    public static final double SLAP_RANGE = 500;
    public static final double SLAP_AREA_ANGLE = 135;
    public static final double SLAP_COOLDOWN = 3.0;
    // AttackConfig Behaviour
    public static final double SLAP_DISTANCE_OFFSET = SLAP_RANGE * 0.8;
    // AttackConfig Animation
    public static final double FADE_OUT_DURATION = 0.6;
    public static final double ENLARGE_DURATION = 3.0;
    public static final double TOTAL_ANIMATION_PERIOD = (FADE_OUT_DURATION + ENLARGE_DURATION);
    public static final double SLAP_RIGID_TIME = TOTAL_ANIMATION_PERIOD * 1.2;

    // Spawn
    public static final double SPAWN_COOLDOWN = 3;
    public static final int MIN_SPAWN = 3;
    public static final int MAX_SPAWN = 7;
    public static final int CHILDREN_SIZE = 1;

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
        return new Rush(RUSH_RANGE, RUSH_COOLDOWN, RUSH_DAMAGE, RUSH_SPEED_MULTIPLY, owner, RUSH_RIGID);
    }

    public static GroundSlap getGroupSlap(Property owner){
        ConeAttackVisual attackVisual = new ConeAttackVisual(SLAP_RANGE, SLAP_AREA_ANGLE, ENLARGE_DURATION, FADE_OUT_DURATION);
        ConeAttackArea attackArea = new ConeAttackArea(SLAP_RANGE, SLAP_AREA_ANGLE);

        return new GroundSlap(attackVisual, SLAP_RIGID_TIME, attackArea, SLAP_DAMAGE, SLAP_COOLDOWN, ENLARGE_DURATION, TOTAL_ANIMATION_PERIOD, SLAP_DISTANCE_OFFSET, owner);
    }

    public static Spawn getSpawn(Spawnable spawnable){
        return new Spawn(SPAWN_COOLDOWN, spawnable);
    }
}
