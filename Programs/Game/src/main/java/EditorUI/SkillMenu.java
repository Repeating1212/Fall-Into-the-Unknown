package EditorUI;

import Data.Config.SkillConfig;
import Data.DataClass.UpgradeText;
import Data.Interface.OverlayController;
import Data.Loader.SceneLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.io.IOException;

public class SkillMenu extends OverlayController {

    @FXML private Pane menuPane, vBox;
    @FXML private Button equipButton;
    @FXML private Text title, description;
    @FXML private ImageView skillView;

    private int skillID;
    private EquipmentObserver observer;
    private SkillConfig skillConfig;

    public void setData(SkillConfig skillConfig, EquipmentObserver observer){
        this.skillID = skillConfig.getConfigID();
        this.skillConfig = skillConfig;
        this.observer = observer;

        title.setText(skillConfig.NAME);
        skillView.setImage(skillConfig.UI_IMAGE);
        description.setText(skillConfig.NAME);
        updateText();
        loadUpgrade();
    }

    @FXML
    private void handleReturn(ActionEvent event) {
        if (rootPane == null) return;
        rootPane.getChildren().remove(menuPane);
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

    // Private Method

    private void updateText(){
        if (fileManager.getGameFile().isEquip(skillID)){
            equipButton.setText("Unequip Skill");
        } else {
            equipButton.setText("Equip Skill");
        }
        observer.reloadData();
    }

    private void loadUpgrade(){
        for(UpgradeText data: skillConfig.getUpgradeTexts()){
            try {
                FXMLLoader loader = new FXMLLoader(SkillMenu.class.getResource(SceneLoader.PaneType.UPGRADE_PANE.getPath()));
                Node skillNode = loader.load();
                UpgradePane upgradePane = loader.getController();
                upgradePane.setFileManager(fileManager);
                upgradePane.setRootPane(rootPane);
                upgradePane.setData(data, observer);
                vBox.getChildren().add(skillNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
