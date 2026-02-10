package BaseLevel.Objects.Class_Concrete;

import BaseLevel.Objects.Class_Base.ImageObject;
import Data.Loader.ImageLoader;
import BaseLevel.Objects.Config.PlayerConfig;
import BaseLevel.Properties.PlayerState;
import Lvl_Sample.Managers.Observer;
import BaseLevel.Skills.Skill;
import BaseLevel.View.AttackVisualize.AttackVisual;
import BaseLevel.Objects.Supplier.PlayerSupplier;
import BaseLevel.Skills.AttackSkill;

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