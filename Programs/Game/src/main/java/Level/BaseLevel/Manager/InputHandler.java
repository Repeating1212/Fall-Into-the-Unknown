package Level.BaseLevel.Manager;

import Level.BaseLevel.Objects.Player;
import Level.BaseLevel.View.SceneView;
import Level.BaseLevel.View.SkillBoxView;

public class InputHandler {

    private final SkillBoxView skillBoxView;

    private final Player player;
    private int previousSkill = 0;
    private int nextSkill = previousSkill;

    private boolean movingUp = false;
    private boolean movingDown = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;

    private boolean pendingSkill = false;
    private int activateSkillID;
    private double mouseX;
    private double mouseY;

    public InputHandler(SceneView sceneView, Player player){
        this.player = player;
        this.skillBoxView = sceneView.getSkillBoxView();
        skillBoxView.updateSkillSelection(previousSkill);
    }

    public void moveUp(boolean isMove){
        movingUp = isMove;
    }
    public void moveDown(boolean isMove){
        movingDown = isMove;
    }
    public void moveLeft(boolean isMove){
        movingLeft = isMove;
    }
    public void moveRight(boolean isMove){
        movingRight = isMove;
    }

    public void skillActivate(double mouseX, double mouseY){
        if (!pendingSkill){
            pendingSkill = true;
            activateSkillID = previousSkill;
            this.mouseX = mouseX;
            this.mouseY = mouseY;
        }
    }

    public void increaseCurrentSkill(){
        if (previousSkill <  3){
            nextSkill = previousSkill + 1;
        }
    }

    public void decreaseCurrentSkill(){
        if(previousSkill > 0) {
            nextSkill = previousSkill - 1;
        }
    }

    public void updateInput(){
        updateMovement();
        if (nextSkill != previousSkill){
            skillBoxView.updateSkillSelection(nextSkill);
            previousSkill = nextSkill;
        }
        if (pendingSkill){
            player.activateSkill(activateSkillID, mouseX, mouseY);
            pendingSkill = false;
        }
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
