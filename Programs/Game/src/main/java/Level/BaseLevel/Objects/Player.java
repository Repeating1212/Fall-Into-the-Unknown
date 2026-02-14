package Level.BaseLevel.Objects;

import Data.DataClass.ArrayData;
import Level.BaseLevel.Objects.Class_Base.DisplayableObject;
import Level.BaseLevel.Objects.Class_Base.ImageObject;
import Data.Loader.ImageLoader;
import Level.BaseLevel.Properties.PlayerState;
import Level.BaseLevel.Skills.Attack;
import Level.BaseLevel.Manager.Observer;
import Level.BaseLevel.Skills.Skill;

public class Player extends ImageObject {
    private final PlayerState playerState;
    private final Skill[] skills;

    // Constructor
    public Player(Skill[] skills) {
        super(PlayerConfig.getProperty(), ImageLoader.PLAYER_LEFT, PlayerConfig.DEAD_DURATION);
        this.skills = skills;
        this.playerState = new PlayerState(PlayerConfig.INVINCIBILITY_PERIOD);
        for(Skill skill : skills){
            skill.initializeData(property, playerState);
        }
    }

    @Override
    public void updateAlive(double deltaTime, Observer observer) {
        handleInvincibilityAnimation();
        playerState.update(deltaTime);
        for(Skill skill : skills){
            skill.handleRigid();
            skill.update(deltaTime, observer);
        }

        super.updateAlive(deltaTime, observer);
    }

    @Override
    public ArrayData<DisplayableObject> reloadDisplay(){
        ArrayData<DisplayableObject> returnArray = super.reloadDisplay();
        if (getAttackVisual() != null && getAttackSkill() != null){
            if (getAttackSkill().isRunning()){
                returnArray.add(getAttackVisual());
            }
        }
        return returnArray;
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

    private  void handleInvincibilityAnimation(){
        if (playerState == null) return;

        if(playerState.isInvincibility()) {
            sprite.setImage(ImageLoader.PLAYER_INVINCIBILITY);
        } else{
            sprite.setImage(ImageLoader.PLAYER_LEFT);
        }
    }

    private DisplayableObject getAttackVisual(){
        for (Skill skill : skills){
            if (skill.getClass() == Attack.class){
                return ((Attack) skill).getVisual();
            }
        }
        return null;
    }

    private Attack getAttackSkill(){
        for (Skill skill : skills){
            if (skill.getClass() == Attack.class){
                return ((Attack) skill);
            }
        }
        return null;
    }
}