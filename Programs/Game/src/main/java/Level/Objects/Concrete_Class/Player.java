package Level.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Level.Data.Config.PlayerConfig;
import Level.Player.PlayerState;
import Level.Objects.Base_Class.ImageObject;
import Level.Managers.Observer;
import Level.Player.Skills.Player.EmptySkill;
import Level.Player.Skills.Skill;
import Level.Player.Skills.Timer;
import Level.View.AttackVisualize.AttackVisual;
import Level.Data.Suppliers.PlayerSupplier;
import Level.Player.Skills.Attacks.AttackSkill;
import javafx.scene.image.ImageView;

public class Player extends ImageObject {
    private final PlayerState playerState;
    private final Skill[] skills;

    // Constructor
    public Player(Observer observer, Skill[] skills) {
        super(PlayerSupplier.getProperty(), ImageLoader.PLAYER_LEFT, observer);
        this.skills = skills;
        this.playerState = new PlayerState(PlayerConfig.INVINCIBILITY_PERIOD);
        for(Skill skill : skills){
            skill.initializeData(property, playerState);
        }
        this.updateHealth();
    }

    // Update player position
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateSpritePosition();
        playerState.update(deltaTime);
        for(Skill skill : skills){
            skill.handleRigid();
            skill.update(deltaTime, observer);
        }
        displaySkillCooldown();
    }

    public void activateSkill(int skillID, double mouseX, double mouseY){
        if(skillID > skills.length) return;
        skills[skillID].activate(mouseX, mouseY);
    }

    // Override Method

    @Override
    protected void updateHealth(){
        observer.updatePlayerHeartView(property.getCurrentHealth());
    }

    @Override
    public boolean takeDamage(double damage){
        if(playerState.isDamageable()){
            super.takeDamage(damage);
            playerState.setDamaged();
            return true;
        }

        return false;
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setScaleX(1);
        else if (property.isMovingRight()) sprite.setScaleX(-1);

        super.updateSpritePosition();
        handleInvincibilityAnimation();

    }

    // Private Method

    private void displaySkillCooldown(){
        for (int i = 0; i < skills.length; i ++){
            observer.updateSkillCooldowns(i, skills[i].getCooldownPercentage());
        }
    }

    // Middle Man Method

    public AttackVisual getAttackVisual() {
        for (Skill skill: skills){
            if (skill.getClass() == AttackSkill.class){
                AttackSkill attackSkill = (AttackSkill) skill;
                return attackSkill.getAttackVisual();
            }
        }
        return null;
    }

    // Private Method

    private  void handleInvincibilityAnimation(){
        if (playerState == null) return;

        if(playerState.isInvincibility()) {
            sprite.setImage(ImageLoader.PLAYER_INVINCIBILITY);
        } else{
            sprite.setImage(ImageLoader.PLAYER_LEFT);
        }
    }
}