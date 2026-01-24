package level.Objects.Concrete_Class;

import level.Objects.Base_Class.ImageObject;
import level.Skills.Player.Dash;
import level.Skills.Player.Defend;
import level.Managers.Observer;
import level.View.AttackVisualize.AttackVisual;
import level.Data.Suppliers.PlayerSupplier;
import level.Skills.Attacks.AttackSkill;
import level.Data.Config.PlayerConfig;

public class Player extends ImageObject {
    private final AttackSkill attackSkill;
    private final Dash dash = PlayerSupplier.getDash();
    private final Defend defend = PlayerSupplier.getDefend();

    // Constructor
    public Player(Observer observer) {
        super(PlayerSupplier.getProperty(), PlayerConfig.loadImage(), observer);
        this.updateHealth();
        this.attackSkill = PlayerSupplier.getAttackBehaviour(property);
    }

    // Update player position
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateSpritePosition();
        handleAttack(deltaTime);
        handleDash(deltaTime);
        defend.update(deltaTime);
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

    // Private Method

    private void handleAttack(double deltaTime){
        attackSkill.handleAttackRigid(property);
        attackSkill.update(deltaTime);
    }

    private void handleDash(Double deltaTime){
        dash.updateDash(property, deltaTime, observer.getObjectProperties());
    }

    private void displaySkillCooldown(){
        double[] skillCooldownPercentage = new double[]{
                attackSkill.getCooldownPercentage(),
                dash.getCooldownPercentage(),
                defend.cooldownPercentage(),
                1
        };
        observer.updateSkillCooldowns(skillCooldownPercentage);
    }

    // Middle Man Method

    public void attack(double mouseX, double mouseY) {
        attackSkill.setAttack(mouseX, mouseY, observer.getLivingEntities());
    }

    public void dash(double mouseX, double mouseY){
        dash.setDash(mouseX, mouseY);
    }

    public void defend(){ defend.setDefend();}

    public AttackVisual getAttackVisual() { return attackSkill.getAttackVisual(); }

}