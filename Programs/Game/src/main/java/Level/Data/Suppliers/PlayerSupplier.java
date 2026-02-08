package Level.Data.Suppliers;

import Level.Data.Properties.PlayerState;
import Level.Skills.Attacks.AttackArea;
import Level.Skills.Attacks.ConeAttackArea;
import Level.Skills.Player.Dash;
import Level.Skills.Player.Defend;
import Level.Data.Config.PlayerConfig;
import Level.Skills.Attacks.AttackSkill;
import Level.Data.Properties.*;
import Level.Skills.Player.EmptySkill;
import Level.View.AttackVisualize.ConeAttackVisual;

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
}
