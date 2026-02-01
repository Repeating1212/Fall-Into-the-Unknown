package Level.Controllers;

import Level.Data.Suppliers.PlayerSupplier;
import Level.Objects.Concrete_Class.Player;
import Level.Skills.Skill;
import Level.View.SkillBoxView;

public class PlayerHandler {

    private final SkillBoxView skillBoxView;
    private boolean classEmpty = false;

    private Player player;
    private int currentSkill = 0;
    private boolean isPause = false;

    private final Skill[] skills = new Skill[]{
            PlayerSupplier.getAttack(),
            PlayerSupplier.getDash(),
            PlayerSupplier.getDefend(),
            PlayerSupplier.getEmptySkill()

    };

    public PlayerHandler (SkillBoxView skillBoxView){
        this.skillBoxView = skillBoxView;
    }

    public void initialize (Player player){
        this.player = player;
        skillBoxView.updateSkillSelection(currentSkill);
        classEmpty = false;
    }

    public Skill[] getSkills(){
        return skills;
    }

    public void moveUp(boolean isMove){
        if (classEmpty) return;
        if (isPause) return;

        player.moveUp(isMove);
    }
    public void moveDown(boolean isMove){
        if (classEmpty) return;
        if (isPause) return;

        player.moveDown(isMove);
    }
    public void moveLeft(boolean isMove){
        if (classEmpty) return;
        if (isPause) return;

        player.moveLeft(isMove);
    }
    public void moveRight(boolean isMove){
        if (classEmpty) return;
        if (isPause) return;

        player.moveRight(isMove);
    }

    public void skillActivate(double mouseX, double mouseY){
        if (classEmpty) return;
        if (isPause) return;

        player.activateSkill(currentSkill, mouseX, mouseY);
    }

    public void increaseCurrentSkill(){
        if (classEmpty) return;
        if (isPause) return;

        if (currentSkill < skills.length - 1){
            currentSkill += 1;
            skillBoxView.updateSkillSelection(currentSkill);
        }
    }

    public void decreaseCurrentSkill(){
        if (classEmpty) return;
        if (isPause) return;

        if(currentSkill > 0) {
            currentSkill -= 1;
            skillBoxView.updateSkillSelection(currentSkill);
        }
    }

    public void setPause(){
        isPause = true;
        // Pause movement
        player.moveDown(false);
        player.moveUp(false);
        player.moveLeft(false);
        player.moveRight(false);
    }

    public void setContinue(){
        isPause = false;
    }
}
