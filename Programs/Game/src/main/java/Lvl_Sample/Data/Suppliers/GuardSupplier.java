package Lvl_Sample.Data.Suppliers;

import BaseLevel.Skills.AttackSkill;
import Lvl_Sample.Data.Config.GuardConfig;
import BaseLevel.Properties.*;
import BaseLevel.Objects.Class_Base.AttackArea;
import BaseLevel.Objects.Class_Concrete.ConeAttackArea;
import BaseLevel.View.AttackVisualize.ConeAttackVisual;

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

    public static AttackSkill getAttackBehaviour(){
        AttackArea attackArea = new ConeAttackArea(GuardConfig.INITIAL_ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE);
        ConeAttackVisual attackVisual = new ConeAttackVisual(GuardConfig.INITIAL_ATTACK_RANGE, GuardConfig.ATTACK_AREA_ANGLE, GuardConfig.ENLARGE_DURATION, GuardConfig.FADE_OUT_DURATION);

        return new AttackSkill(attackVisual, GuardConfig.ATTACK_RIGID_TIME, attackArea, GuardConfig.INITIAL_DAMAGE, GuardConfig.INITIAL_ATTACK_COOLDOWN, GuardConfig.ENLARGE_DURATION);
    }
}
