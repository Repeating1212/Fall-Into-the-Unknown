package Level.View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SkillBoxView {

    private Image skill;
    private ImageView[] skills;
    private StackPane[] skillBackgrounds;
    private Rectangle[] skillCooldowns;

    public SkillBoxView (ImageView[] skills, StackPane[] skillBackgrounds, Rectangle[] skillCooldowns){
        this.skills = skills;
        this.skillBackgrounds = skillBackgrounds;
        this.skillCooldowns = skillCooldowns;
    }

    public void updateSkillSelection(int currentSkill) {
        for (int i = 0; i < skillBackgrounds.length; i++) {
            if (i == currentSkill) {
                skillBackgrounds[i].setOpacity(1.0);
                skillBackgrounds[i].setStyle("-fx-background-color: black;");
            } else {
                skillBackgrounds[i].setOpacity(0.5);
                skillBackgrounds[i].setStyle("-fx-background-color: transparent;");
            }
        }
    }

    public void updateSkillCooldowns(int skillID , double cooldownPercentages){
        if (skillID < 0 || skillID > 3) return;
        skillCooldowns[skillID].setWidth(cooldownPercentages * skills[skillID].getFitWidth());
    }
}
