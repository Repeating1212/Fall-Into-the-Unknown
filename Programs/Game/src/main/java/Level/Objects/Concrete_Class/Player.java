package Level.Objects.Concrete_Class;

import Level.Objects.Base_Class.ImageObject;
import Level.Skills.Player.Dash;
import Level.Skills.Player.Defend;
import Level.Managers.Observer;
import Level.Skills.Skill;
import Level.View.AttackVisualize.AttackVisual;
import Level.Data.Suppliers.PlayerSupplier;
import Level.Skills.Attacks.AttackSkill;
import Level.Data.Config.PlayerConfig;

public class Player extends ImageObject {
    private final AttackSkill attackSkill = PlayerSupplier.getAttackBehaviour(property);
    private final Dash dash = PlayerSupplier.getDash(property);
    private final Defend defend = PlayerSupplier.getDefend(property);
    private final Skill[] skills = new Skill[]{
            attackSkill, dash, defend, null
    };

    // Constructor
    public Player(Observer observer) {
        super(PlayerSupplier.getProperty(), PlayerConfig.loadImageRight(), observer);
        this.updateHealth();
    }

    // Update player position
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateSpritePosition();
        for(Skill skill : skills){
            if (skill == null) continue;
            skill.handleRigid();
            skill.update(deltaTime, observer);
        }
        displaySkillCooldown();
    }

    // Override Method

    @Override
    protected void updateHealth(){
        observer.updatePlayerHeartView(property.getCurrentHealth());
    }

    @Override
    public void takeDamage(double damage){
        if(! defend.isDefending()){
            super.takeDamage(damage);
        }
    }

    @Override
    public void updateSpritePosition(){
        if (property.isMovingLeft()) sprite.setImage(PlayerConfig.loadImageLeft());
        else if (property.isMovingRight()) sprite.setImage(PlayerConfig.loadImageRight());
        super.updateSpritePosition();
    }

    // Private Method

    private void displaySkillCooldown(){
        for (int i = 0; i < skills.length; i ++){
            if (skills[i] == null) return;
            observer.updateSkillCooldowns(i, skills[i].getCooldownPercentage());
        }
    }

    // Middle Man Method

    public void attack(double mouseX, double mouseY) {
        attackSkill.activate(mouseX, mouseY);
    }

    public void dash(double mouseX, double mouseY){
        dash.activate(mouseX, mouseY);
    }

    public void defend(){
        defend.activate(null);
    }

    public AttackVisual getAttackVisual() { return attackSkill.getAttackVisual(); }

}