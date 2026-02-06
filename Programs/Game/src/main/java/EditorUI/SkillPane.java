package EditorUI;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Supplier.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class SkillPane extends PaneInterface{

    @FXML private Text skillName;
    @FXML private ImageView skillView;
    @FXML private  Text skillStatus;

    private int skillID;
    private SkillConfig skillConfig;
    private Equipment equipment;

    public void setData(SkillConfig skillConfig, Equipment equipment){
        this.skillConfig = skillConfig;
        this.equipment = equipment;
        this.skillID = skillConfig.CONFIG_ID;
        skillView.setImage(skillConfig.IMAGE);
        skillName.setText(skillConfig.NAME);
        updateText();
    }

    @FXML
    private void loadMenu(){
        SkillMenu skillMenu = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SKILL_MENU, fileManager);
        assert skillMenu != null;
        skillMenu.setData(skillConfig, equipment);
    }

    private void updateText(){
        if (fileManager.getGameFile().isEquip(skillID)){
            skillStatus.setText("✓ Equipped");
        } else {
            skillStatus.setText("");
        }
    }
}
