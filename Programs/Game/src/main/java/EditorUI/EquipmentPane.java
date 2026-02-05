package EditorUI;

import Game_Data.Interface.PaneInterface;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class EquipmentPane extends PaneInterface {

    @FXML private Button equipButton;
    @FXML private ImageView skillIcon;
    @FXML private HBox background;
    @FXML private HBox sideBoard;
    @FXML private Text text;

    private int skillID;

    public void setData(int skillID, Image skillImage){
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
        if (fileManager.getGameFile().isEquip(skillID)){
            fileManager.getGameFile().setUnequip(skillID);
        } else {
            fileManager.getGameFile().setEquip(skillID);
        }
        updateText();
    }

    private void updateText(){
        if (fileManager.getGameFile().isEquip(skillID)){
            equipButton.setText("Equiped");
        } else {
            equipButton.setText("Unequip");
        }
    }
}
