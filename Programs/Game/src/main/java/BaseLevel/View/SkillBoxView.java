package BaseLevel.View;

import Data.Supplier.SkillSupplier;
import LoadFile.FileManager;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

public class SkillBoxView {

    private ImageView[] skillView;
    private StackPane[] skillBackgrounds;
    private Rectangle[] skillCooldowns;
    private final FileManager fileManager;

    public SkillBoxView (ImageView[] skills, StackPane[] skillBackgrounds, Rectangle[] skillCooldowns, FileManager fileManager){
        this.skillView = skills;
        this.skillBackgrounds = skillBackgrounds;
        this.skillCooldowns = skillCooldowns;
        this.fileManager = fileManager;
        setSkillImage();
    }

    public void setSkillImage(){
        Image[] images = SkillSupplier.getImages_Level(fileManager);
        for (int i = 0; i < skillView.length; i ++){
            skillView[i].setImage(images[i]);
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
