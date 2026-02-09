package Level.Controllers;

import Level.Data.Data.Direction;
import Level.Objects.Concrete_Class.Player;
import Level.View.SkillBoxView;

public class PlayerHandler {

    private final SkillBoxView skillBoxView;

    private final Player player;
    private int currentSkill = 0;
    private boolean isPause = false;

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    public PlayerHandler (SkillBoxView skillBoxView, Player player){
        this.player = player;
        this.skillBoxView = skillBoxView;
        skillBoxView.updateSkillSelection(currentSkill);
    }

    public void moveUp(boolean isMove){
        if (isPause) return;

        movingUp = isMove;
        updateMovement();
    }
    public void moveDown(boolean isMove){
        if (isPause) return;

        movingDown = isMove;
        updateMovement();
    }
    public void moveLeft(boolean isMove){
        if (isPause) return;

        movingLeft = isMove;
        updateMovement();
    }
    public void moveRight(boolean isMove){
        if (isPause) return;

        movingRight = isMove;
        updateMovement();
    }

    public void skillActivate(double mouseX, double mouseY){
        if (isPause) return;

        player.activateSkill(currentSkill, mouseX, mouseY);
    }

    public void increaseCurrentSkill(){
        if (isPause) return;

        if (currentSkill <  3){
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
        movingUp = false;
        movingDown = false;
        movingRight = false;
        movingLeft = false;
        updateMovement();
    }

    public void setContinue(){
        isPause = false;
    }

    // Private Method

    private void updateMovement(){
        Direction direction = Direction.fromKeys(movingUp, movingDown, movingLeft, movingRight);
        player.setMovement(direction.getXMultiplier(), direction.getYMultiplier());
    }
}
