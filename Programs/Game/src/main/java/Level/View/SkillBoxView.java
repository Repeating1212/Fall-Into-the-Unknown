package Level.View;

import Game_Data.ImageLoader;
import Level.Skills.Attacks.AttackSkill;
import Level.Skills.Player.Dash;
import Level.Skills.Player.Defend;
import Level.Skills.Skill;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SkillBoxView {

    private ImageView[] skillView;
    private StackPane[] skillBackgrounds;
    private Rectangle[] skillCooldowns;

    public SkillBoxView (ImageView[] skills, StackPane[] skillBackgrounds, Rectangle[] skillCooldowns){
        this.skillView = skills;
        this.skillBackgrounds = skillBackgrounds;
        this.skillCooldowns = skillCooldowns;
    }

    public void setSkillImage(Skill[] skills){
        for (int i = 0; i < skills.length; i ++){
            if (skills[i].getClass() == AttackSkill.class){
                skillView[i].setImage(ImageLoader.ATTACK_ICON);
            } else if(skills[i].getClass() == Defend.class){
                skillView[i].setImage(ImageLoader.DEFEND_ICON);
            } else if (skills[i].getClass() == Dash.class){
                skillView[i].setImage(ImageLoader.CMP_DASH_ICON);
            } else {
                skillView[i].setImage(ImageLoader.EMPTY_ICON);
            }
        }
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
        skillCooldowns[skillID].setWidth(cooldownPercentages * skillView[skillID].getFitWidth());
    }
}
