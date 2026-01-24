package level.Managers;

import level.Objects.Concrete_Class.Player;
import level.View.SkillBoxView;

public class PlayerHandler {


    private final SkillBoxView skillBoxView;
    private final int ATTACK_SKILL_ID = 0;
    private final int DASH_SKILL_ID = 1;
    private boolean classEmpty = false;

    private Player player;
    private int currentSkill = 0;
    private int MAXIMUM_SKILL = 3;

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
        player.moveUp(isMove);
    }
    public void moveDown(boolean isMove){
        if (classEmpty) return;
        player.moveDown(isMove);
    }
    public void moveLeft(boolean isMove){
        if (classEmpty) return;
        player.moveLeft(isMove);
    }
    public void moveRight(boolean isMove){
        if (classEmpty) return;
        player.moveRight(isMove);
    }

    public void skillActivate(double mouseX, double mouseY){
        if (classEmpty) return;

        switch (currentSkill){
            case 0:
                player.attack(mouseX,mouseY);
                break;
            case 1:
                player.dash(mouseX,mouseY);
                break;
            case 2:
                player.defend();
                break;
        }
    }

    public void increaseCurrentSkill(){
        if (currentSkill < MAXIMUM_SKILL){
            currentSkill += 1;
            skillBoxView.updateSkillSelection(currentSkill);
        }
    }

    public void decreaseCurrentSkill(){
        if(currentSkill > 0) {
            currentSkill -= 1;
            skillBoxView.updateSkillSelection(currentSkill);
        }
    }
}
