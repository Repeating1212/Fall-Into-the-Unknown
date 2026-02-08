package EditorUI;

import Data.Config.SkillConfig;
import Data.Interface.PaneInterface;
import Data.Supplier.SceneLoader;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SkillPane extends PaneInterface{

    @FXML private Text skillName;
    @FXML private ImageView skillView;
    @FXML private  Text skillStatus;
    @FXML private Pane pane;

    private int skillID;
    private SkillConfig skillConfig;
    private EquipmentObserver observer
            ;

    public void setData(SkillConfig skillConfig, EquipmentObserver observer){
        this.skillConfig = skillConfig;
        this.observer = observer;
        this.skillID = skillConfig.getConfigID();
        skillView.setImage(skillConfig.UI_IMAGE);
        skillName.setText(skillConfig.NAME);
        updateText();
        handleSelected();
    }

    @FXML
    private void loadMenu(){
        SkillMenu skillMenu = SceneLoader.loadOverlayScene(rootPane, SceneLoader.SceneType.SKILL_MENU, fileManager);
        assert skillMenu != null;
        skillMenu.setData(skillConfig, observer);
    }

    private void updateText(){
        if (fileManager.getGameFile().isEquip(skillID)){
            skillStatus.setText("✓ Equipped");
        } else {
            skillStatus.setText("");
        }
    }

    private void handleSelected() {
        int[] equipment = fileManager.getGameFile().getEquipment();
        for (int skill : equipment){
            if (skill == skillID){
                pane.getStyleClass().add("selectedPane");
                pane.getStyleClass().remove("pane");

            }
        }
    }
}
