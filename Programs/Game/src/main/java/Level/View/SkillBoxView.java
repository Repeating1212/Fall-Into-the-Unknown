package Level.View;

import Level.Skills.Attacks.AttackSkill;
import Level.Skills.Player.Dash;
import Level.Skills.Player.Defend;
import Level.Skills.Skill;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SkillBoxView {

    private final Image attack = new Image(getClass().getResourceAsStream("/Skill_Icon/Cmp_Attack.png"));
    private final Image defend = new Image(getClass().getResourceAsStream("/Skill_Icon/Cmp_Defend.png"));
    private final Image dash = new Image(getClass().getResourceAsStream("/Skill_Icon/Cmp_Dash.png"));
    private final Image emptySkill = new Image(getClass().getResourceAsStream("/Skill_Icon/Empty_Skill.png"));

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
                skillView[i].setImage(attack);
            } else if(skills[i].getClass() == Defend.class){
                skillView[i].setImage(defend);
            } else if (skills[i].getClass() == Dash.class){
                skillView[i].setImage(dash);
            } else {
                skillView[i].setImage(emptySkill);
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
