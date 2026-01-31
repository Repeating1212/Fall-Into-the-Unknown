package Game_UI.Skill_Scene;

import Game_Data.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SkillController {
    @FXML private Button equipButton;
    @FXML private ImageView skillIcon;
    private int skillID;

    public void initializeData(int skillID, Image skillImage){
        this.skillID = skillID;
        skillIcon.setImage(skillImage);
        updateText();
    }

    @FXML
    private void equipSkill() {
        if (DataManager.getSkillData().isEquip(skillID)){
            DataManager.getSkillData().setUnequip(skillID);
        } else {
            DataManager.getSkillData().setEquip(skillID);
        }
        updateText();
    }

    private void updateText(){
        if (DataManager.getSkillData().isEquip(skillID)){
            equipButton.setText("Equiped");
        } else {
            equipButton.setText("Unequip");
        }
    }
}
