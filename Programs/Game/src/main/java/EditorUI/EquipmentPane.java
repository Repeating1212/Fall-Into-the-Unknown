package EditorUI;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Supplier.ImageLoader;
import Data.Supplier.SceneLoader;
import Data.Supplier.SkillSupplier;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class EquipmentPane extends PaneInterface {

    @FXML private ImageView equipmentView;
    @FXML private Text equipmentText;

    private SkillConfig skillConfig;
    private EquipmentObserver observer;

    public void setData(int skillID, EquipmentObserver equipmentObserver){
        this.observer = equipmentObserver;
        this.skillConfig = SkillSupplier.getSkillConfig(skillID);

        equipmentText.setText(skillConfig.NAME);
        equipmentView.setImage(skillConfig.IMAGE);
//        ImageLoader.clipImage(equipmentView);
    }

    @FXML
    private void loadMenu(){
        SkillMenu skillMenu = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SKILL_MENU, fileManager);
        assert skillMenu != null;
        skillMenu.setData(skillConfig, observer);
    }
}
