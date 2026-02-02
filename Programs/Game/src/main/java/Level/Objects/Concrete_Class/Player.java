package Level.Objects.Concrete_Class;

import Game_Data.Config.ImageConfig;
import Level.Data.Properties.PlayerState;
import Level.Objects.Base_Class.ImageObject;
import Level.Managers.Observer;
import Level.Skills.Skill;
import Level.View.AttackVisualize.AttackVisual;
import Level.Data.Suppliers.PlayerSupplier;
import Level.Skills.Attacks.AttackSkill;
import Level.Data.Config.PlayerConfig;

public class Player extends ImageObject {
    private final PlayerState playerState = new PlayerState();

    private final Skill[] skills;

    // Constructor
    public Player(Observer observer, Skill[] skills) {
        super(PlayerSupplier.getProperty(), ImageConfig.PLAYER_RIGHT, observer);
        this.updateHealth();
        this.skills = skills;
        for (Skill skill : this.skills){
            skill.initializeData(property, playerState);
        }
    }

    // Update player position
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateSpritePosition();
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
    public void takeDamage(double damage){
        if(! playerState.isDefend()){
            super.takeDamage(damage);
        }
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setImage(ImageConfig.PLAYER_LEFT);
        else if (property.isMovingRight()) sprite.setImage(ImageConfig.PLAYER_RIGHT);
        super.updateSpritePosition();
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
}