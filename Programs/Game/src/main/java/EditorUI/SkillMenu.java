package EditorUI;

import Data.Config.SkillConfig;
import Data.DataClass.UpgradeText;
import Data.Interface.OverlayController;
import Data.Supplier.SceneLoader;
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
    private Equipment equipment;
    private SkillConfig skillConfig;

    public void setData(SkillConfig skillConfig, Equipment equipment){
        this.skillID = skillConfig.CONFIG_ID;
        this.skillConfig = skillConfig;
        this.equipment = equipment;

        title.setText(skillConfig.NAME);
        skillView.setImage(skillConfig.IMAGE);
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
            equipButton.setText("Equip Skill");
        } else {
            equipButton.setText("Unequip Skill");
        }
        equipment.reloadData();
    }

    private void loadUpgrade(){
        for(UpgradeText data: skillConfig.getUpgradeTexts()){
            try {
                FXMLLoader loader = new FXMLLoader(SkillMenu.class.getResource(SceneLoader.PaneType.UPGRADE_PANE.getPath()));
                Node skillNode = loader.load();
                UpgradePane upgradePane = loader.getController();
                upgradePane.setFileManager(fileManager);
                upgradePane.setRootPane(rootPane);
                upgradePane.initializeData(data, equipment);
                vBox.getChildren().add(skillNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
