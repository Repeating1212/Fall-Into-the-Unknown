package Level.Controllers;

import LoadFile.DataManager;
import Level.Objects.Concrete_Class.Player;
import Level.View.SkillBoxView;

public class PlayerHandler {

    private final SkillBoxView skillBoxView;

    private final Player player;
    private int currentSkill = 0;
    private boolean isPause = false;

    public PlayerHandler (SkillBoxView skillBoxView, Player player){
        this.player = player;
        this.skillBoxView = skillBoxView;
        skillBoxView.updateSkillSelection(currentSkill);
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

        if (currentSkill <  DataManager.getGameFile().getSkillLength() - 1){
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
