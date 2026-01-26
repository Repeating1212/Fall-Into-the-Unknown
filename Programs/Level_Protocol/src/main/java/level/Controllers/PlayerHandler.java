package level.Controllers;

import level.Objects.Concrete_Class.Player;
import level.View.SkillBoxView;

public class PlayerHandler {

    private final SkillBoxView skillBoxView;
    private final int ATTACK_SKILL_ID = 0;
    private final int DASH_SKILL_ID = 1;
    private final int DEFEND_SKILL_ID = 2;
    private boolean classEmpty = false;

    private Player player;
    private int currentSkill = 0;
    private final int MAXIMUM_SKILL = 3;
    private boolean isPause = false;

    public PlayerHandler (SkillBoxView skillBoxView){
        this.skillBoxView = skillBoxView;
    }

    public void initialize (Player player){
        this.player = player;
        skillBoxView.updateSkillSelection(currentSkill);
        classEmpty = false;
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

        switch (currentSkill){
            case ATTACK_SKILL_ID:
                player.attack(mouseX,mouseY);
                break;
            case DASH_SKILL_ID:
                player.dash(mouseX,mouseY);
                break;
            case DEFEND_SKILL_ID:
                player.defend();
                break;
        }
    }

    public void increaseCurrentSkill(){
        if (classEmpty) return;
        if (isPause) return;

        if (currentSkill < MAXIMUM_SKILL){
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
