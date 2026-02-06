package EditorUI.Temp;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Supplier.SkillSupplier;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class EquipmentPane extends PaneInterface {
    @FXML private ImageView equipmentView;
    @FXML private Text equipmentText;

    public void setData(int currentSkill){
        SkillConfig skillConfig = SkillSupplier.getSkillConfig(currentSkill);
        equipmentText.setText(skillConfig.NAME);
        equipmentView.setImage(skillConfig.IMAGE);
    }
}
