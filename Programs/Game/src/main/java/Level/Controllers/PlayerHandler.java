package Level.Controllers;

import Game_Data.DataManager;
import Game_Data.Config.SkillConfig;
import Level.Objects.Concrete_Class.Player;
import Level.Skills.Skill;
import Level.View.SkillBoxView;

public class PlayerHandler {

    private final SkillBoxView skillBoxView;

    private final Player player;
    private int currentSkill = 0;
    private boolean isPause = false;

    private final Skill[] skills = new Skill[4];

    public PlayerHandler (SkillBoxView skillBoxView, Player player){
        this.player = player;
        this.skillBoxView = skillBoxView;

        // Update skill data
        int[] equipedSkill = DataManager.getGameData().getEquipedSkill();
        for (int i = 0; i < equipedSkill.length; i++){
            skills[i] = SkillConfig.getPlayerSkill(equipedSkill[i]);
        }
        skillBoxView.setSkillImage(skills);
        skillBoxView.updateSkillSelection(currentSkill);
        player.setSkills(skills);
    }

    public Skill[] getSkills(){
        return skills;
    }

    public void moveUp(boolean isMove){
        if (isPause) return;

        player.moveUp(isMove);
    }
    public void moveDown(boolean isMove){
        if (isPause) return;

        player.moveDown(isMove);
    }
    public void moveLeft(boolean isMove){
        if (isPause) return;

        player.moveLeft(isMove);
    }
    public void moveRight(boolean isMove){
        if (isPause) return;

        player.moveRight(isMove);
    }

    public void skillActivate(double mouseX, double mouseY){
        if (isPause) return;

        player.activateSkill(currentSkill, mouseX, mouseY);
    }

    public void increaseCurrentSkill(){
        if (isPause) return;

        if (currentSkill < skills.length - 1){
            currentSkill += 1;
            skillBoxView.updateSkillSelection(currentSkill);
        }
    }

    public void decreaseCurrentSkill(){
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
