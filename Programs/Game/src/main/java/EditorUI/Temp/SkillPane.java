package EditorUI.Temp;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SkillPane extends PaneInterface{

    @FXML private Text skillName;
    @FXML private ImageView skillView;
    @FXML private  Text skillStatus;

    private int skillID;

    public void setData(SkillConfig skillConfig){
        this.skillID = skillConfig.CONFIG_ID;
        skillView.setImage(skillConfig.IMAGE);
        skillName.setText(skillConfig.NAME);
        updateText();
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
            skillStatus.setText("✓ Equipped");
        } else {
            skillStatus.setText("");
        }
    }
}
