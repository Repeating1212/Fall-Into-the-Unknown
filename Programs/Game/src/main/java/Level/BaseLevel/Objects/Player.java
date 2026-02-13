package Level.BaseLevel.Objects;

import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.PlayerState;
import Level.BaseLevel.Skills.Attack;
import Level.BaseLevel.View.AttackVisualize.AttackVisual;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Skills.Skill;

public class Player extends ImageObject {
    private final PlayerState playerState;
    private final Skill[] skills;

    // Constructor
    public Player(Skill[] skills) {
        super(PlayerConfig.getProperty(), ImageLoader.PLAYER_LEFT);
        this.skills = skills;
        this.playerState = new PlayerState(PlayerConfig.INVINCIBILITY_PERIOD);
        for(Skill skill : skills){
            skill.initializeData(property, playerState);
        }
        this.initializeDisplays();
    }

    // Update player position
    public void update(double deltaTime, Observer observer) {

        super.update(deltaTime, observer);
        handleInvincibilityAnimation();

        playerState.update(deltaTime);

        for(Skill skill : skills){
            skill.handleRigid();
            skill.update(deltaTime, observer);
        }

        if (getAttackVisual() != null){
            if (getAttackVisual().isActive()){
                relatedDisplay.add(getAttackVisual());
            } else{
                relatedDisplay.remove(getAttackVisual());
            }
        }
    }

    public void activateSkill(int skillID, double mouseX, double mouseY){
        if(skillID > skills.length) return;
        skills[skillID].activate(mouseX, mouseY);
    }

    public double[] getSkillCooldown(){
        double[] cooldowns = new double[skills.length];
        for (int i = 0; i < skills.length; i++){
            cooldowns[i] = skills[i].getCooldownPercentage();
        }
        return cooldowns;
    }

    // Override Method

    @Override
    public boolean takeDamage(double damage){
        if(playerState.isDamageable()){
            super.takeDamage(damage);
            playerState.setDamaged();
            return true;
        }
        return false;
    }

    // Private Method

    private void initializeDisplays(){
        AttackVisual attackVisual = getAttackVisual();
        if (attackVisual != null) relatedDisplay.add(attackVisual);
    }

    private  void handleInvincibilityAnimation(){
        if (playerState == null) return;

        if(playerState.isInvincibility()) {
            sprite.setImage(ImageLoader.PLAYER_INVINCIBILITY);
        } else{
            sprite.setImage(ImageLoader.PLAYER_LEFT);
        }
    }

    private AttackVisual getAttackVisual(){
        for (Skill skill : skills){
            if (skill.getClass() == Attack.class){
                return ((Attack) skill).getAttackVisual();
            }
        }
        return null;
    }
}