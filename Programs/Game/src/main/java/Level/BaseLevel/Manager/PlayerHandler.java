package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Class_Concrete.Player;
import Level.BaseLevel.View.SkillBoxView;

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

    private void updateMovement() {
        double movementX = (movingRight ? 1 : 0) - (movingLeft ? 1 : 0);
        double movementY = (movingDown ? 1 : 0) - (movingUp ? 1 : 0);

        if (movementX != 0 && movementY != 0) {
            double normalizeFactor = Math.sqrt(2) / 2; // a^2 + b^2 = c^2
            movementX *= normalizeFactor;
            movementY *= normalizeFactor;
        }

        player.setMovement( movementX, movementY);
    }
}
