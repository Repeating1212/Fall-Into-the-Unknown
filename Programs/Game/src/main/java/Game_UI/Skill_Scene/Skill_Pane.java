package Game_UI.Skill_Scene;

import LoadFile.DataManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Skill_Pane {

    @FXML private Button equipButton;
    @FXML private ImageView skillIcon;
    @FXML private HBox background;
    @FXML private HBox sideBoard;
    @FXML private Text text;

    private int skillID;

    public void initializeData(int skillID, Image skillImage){
        this.skillID = skillID;
        skillIcon.setImage(skillImage);
        updateText();
        background.getStyleClass().add("background_highlighted");
        sideBoard.getStyleClass().add("sideBoard_highlighted");
        sideBoard.getStyleClass().remove("sideBoard");
        text.getStyleClass().add("text_highlighted");
    }

    @FXML
    private void equipSkill() {
        if (DataManager.getGameFile().isEquip(skillID)){
            DataManager.getGameFile().setUnequip(skillID);
        } else {
            DataManager.getGameFile().setEquip(skillID);
        }
        updateText();
    }

    private void updateText(){
        if (DataManager.getGameFile().isEquip(skillID)){
            equipButton.setText("Equiped");
        } else {
            equipButton.setText("Unequip");
        }
    }
}
