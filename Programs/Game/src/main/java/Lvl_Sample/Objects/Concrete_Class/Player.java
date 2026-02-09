package Lvl_Sample.Objects.Concrete_Class;

import Data.Loader.ImageLoader;
import Lvl_Sample.Data.Properties.PlayerState;
import Lvl_Sample.Objects.Base_Class.ImageObject;
import Lvl_Sample.Managers.Observer;
import Lvl_Sample.Skills.Player.EmptySkill;
import Lvl_Sample.Skills.Skill;
import Lvl_Sample.View.AttackVisualize.AttackVisual;
import Lvl_Sample.Data.Suppliers.PlayerSupplier;
import Lvl_Sample.Skills.Attacks.AttackSkill;

public class Player extends ImageObject {
    private final PlayerState playerState = new PlayerState();
    private Skill[] skills = new Skill[]{
            new EmptySkill(), new EmptySkill(), new EmptySkill(), new EmptySkill()
    };

    // Constructor
    public Player(Observer observer, Skill[] skills) {
        super(PlayerSupplier.getProperty(), ImageLoader.PLAYER_RIGHT, observer);
        this.updateHealth();
        this.skills = skills;
        for(Skill skill : skills){
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
        if (property.isMovingLeft()) sprite.setImage(ImageLoader.PLAYER_LEFT);
        else if (property.isMovingRight()) sprite.setImage(ImageLoader.PLAYER_RIGHT);
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