package Level.Lvl_2.Enemy.Config;

import Level.BaseLevel.Properties.*;
import Level.Lvl_1.Behaviour.Behaviour;
import Level.Lvl_1.Behaviour.Tackle;
import Level.Lvl_2.Enemy.Behaviour.SpeedUp;

public class SkeletonConfig {

    public enum SkeletonState{
        STATE01, STATE02, STATE03, STATE04;

        public int getHeight(){
            return switch (this){
                case STATE01 -> 60;
                case STATE02 -> 45;
                case STATE03 -> 30;
                case STATE04 -> 15;
            };
        }

        public int getSpeed(){
            return switch (this){
                case STATE01 -> 200;
                case STATE02 -> 175;
                case STATE03 -> 150;
                case STATE04 -> 125;
            };
        }

        public static SkeletonState checkState(int health){
            if (health > 30) return STATE01;
            if (health > 20) return STATE02;
            if (health > 10) return STATE03;
            return STATE04;
        }
    }

    public static final int HEIGHT = SkeletonState.STATE01.getHeight();
    public static final int WIDTH = 30;
    public static final double SPEED  = SkeletonState.STATE01.getSpeed();
    public static final int INITIAL_X = 600;
    public static final int INITIAL_Y = 100;
    public static final boolean IS_BLOCKABLE = true;

    public static final int MAXIMUM_HEALTH = 40;
    public static final double DEAD_DURATION = 1.0;

    public static final double DAMAGE_RIGID_DURATION = 2.0;
    public static final double TACKLE_DAMAGE = 1;
    public static final double TACKLE_RIGID = 2.5;

    // Speed Up Behaviour
    public static final double SPEED_UP_DURATION = 1.0;
    public static final int    SPEED_UP_VALUE    = 100;
    public static final double SPEED_UP_COOLDOWN = 0.0;

    // Supplier

    public static Property getProperty(){
        Status status = new Status();
        Position position =  new Position(SkeletonConfig.INITIAL_X, SkeletonConfig.INITIAL_Y);
        HitBox hitBox = new HitBox(SkeletonConfig.WIDTH, SkeletonConfig.HEIGHT, position, SkeletonConfig.IS_BLOCKABLE, status);
        MovementState movementState = new MovementState(SkeletonConfig.SPEED, status);
        Health health = new Health(SkeletonConfig.MAXIMUM_HEALTH, status);
        return new Property(hitBox, movementState, health, status);
    }

    public static Tackle getTackle(Property owner){
        return new Tackle(SkeletonConfig.TACKLE_DAMAGE, SkeletonConfig.TACKLE_RIGID, owner);
    }

    public static SpeedUp getSpeedUp(Property owner){
        return new SpeedUp(SPEED_UP_COOLDOWN, owner, SPEED_UP_VALUE, SPEED_UP_DURATION);
    }
}
