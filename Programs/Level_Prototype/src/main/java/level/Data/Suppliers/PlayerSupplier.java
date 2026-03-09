package level.Data.Suppliers;

import level.Skills.Attacks.AttackArea;
import level.Skills.Attacks.ConeAttackArea;
import level.Skills.Player.Dash;
import level.Skills.Player.Defend;
import level.Data.Config.PlayerConfig;
import level.Skills.Attacks.AttackSkill;
import level.Data.Properties.*;
import level.View.AttackVisualize.ConeAttackVisual;

public class PlayerSupplier {

    public static Property getProperty(){
        Position playerPosition =  new Position(PlayerConfig.INITIAL_X, PlayerConfig.INITIAL_Y);
        HitBox playerHitBox = new HitBox(PlayerConfig.WIDTH, PlayerConfig.HEIGHT, playerPosition, PlayerConfig.IS_BLOCKABLE);
        MovementState playerMovement = new MovementState(PlayerConfig.SPEED);
        Health playerHealth = new Health(PlayerConfig.MAXIMUM_HEALTH);
        return new Property(playerHitBox, playerMovement, playerHealth);
    }

    public static Health getHealth(){
        return new Health(PlayerConfig.MAXIMUM_HEALTH);
    }

    public static AttackSkill getAttackBehaviour(Property player){
        AttackArea attackArea = new ConeAttackArea(PlayerConfig.INITIAL_ATTACK_RANGE, PlayerConfig.ATTACK_AREA_ANGLE);
        ConeAttackVisual attackVisual = new ConeAttackVisual(PlayerConfig.INITIAL_ATTACK_RANGE, PlayerConfig.ATTACK_AREA_ANGLE, PlayerConfig.ENLARGE_DURATION, PlayerConfig.FADE_OUT_DURATION);

        return new AttackSkill(attackVisual, PlayerConfig.ATTACK_RIGID_TIME, player, attackArea, PlayerConfig.INITIAL_DAMAGE, PlayerConfig.INITIAL_ATTACK_COOLDOWN, PlayerConfig.ENLARGE_DURATION);
    }

    public static Dash getDash(){
        return new Dash(PlayerConfig.DASH_COOLDOWN, PlayerConfig.DASH_RANGE);
    }

    public static Defend getDefend() {
        return new Defend(PlayerConfig.DEFEND_COOLDOWN, PlayerConfig.DEFEND_DURATION);
    }
}
