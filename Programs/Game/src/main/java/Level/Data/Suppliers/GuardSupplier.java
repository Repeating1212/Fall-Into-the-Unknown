package Level.Data.Suppliers;

import Level.Skills.Attacks.AttackSkill;
import Level.Data.Config.GuardConfig;
import Level.Data.Properties.*;
import Level.Skills.Attacks.AttackArea;
import Level.Skills.Attacks.ConeAttackArea;
import Level.View.AttackVisualize.ConeAttackVisual;

public class GuardSupplier {
    public static Property getProperty(){
        Position guardPosition =  new Position(GuardConfig.INITIAL_X, GuardConfig.INITIAL_Y);
        HitBox guardHitBox = new HitBox(GuardConfig.WIDTH, GuardConfig.HEIGHT, guardPosition, GuardConfig.IS_BLOCKABLE);
        MovementState guardMovementState = new MovementState(GuardConfig.SPEED);
        Health guardHeart = new Health(GuardConfig.MAXIMUM_HEALTH);
        return new Property(guardHitBox, guardMovementState, guardHeart);
    }

    public static Health getHealth(){
        return new Health(GuardConfig.MAXIMUM_HEALTH);
    }

    public static AttackSkill getAttackBehaviour(Property guard){
        AttackArea attackArea = new ConeAttackArea(GuardConfig.INITIAL_ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE);
        ConeAttackVisual attackVisual = new ConeAttackVisual(GuardConfig.INITIAL_ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE, GuardConfig.ENLARGE_DURATION, GuardConfig.FADE_OUT_DURATION);

        return new AttackSkill(attackVisual, GuardConfig.ATTACK_RIGID_TIME, guard, attackArea, GuardConfig.INITIAL_DAMAGE, GuardConfig.INITIAL_ATTACK_COOLDOWN, GuardConfig.ENLARGE_DURATION);
    }
}
