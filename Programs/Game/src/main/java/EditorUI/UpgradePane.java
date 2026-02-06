package EditorUI;

import Data.DataClass.UpgradeText;
import Data.DataClass.UpgradeValue;
import Data.Interface.PaneInterface;
import Data.Supplier.ImageLoader;
import Data.Supplier.SkillSupplier;
import LoadFile.SkillFile.SkillFile;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class UpgradePane extends PaneInterface {

    @FXML private Text title, description;
    @FXML private Text costLabel;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView coinView;

    private UpgradeText upgradeText;
    private UpgradeValue upgradeValue;
    private Equipment equipment;

    public void initialize(){
        title.setText("Title");
        description.setText("Description");
        progressBar.setProgress(1.0);
        costLabel.setText("0");

        coinView.setImage(ImageLoader.COIN);
    }

    public void initializeData(UpgradeText upgradeText, Equipment equipment) {
        this.upgradeText = upgradeText;
        this.equipment = equipment;
        this.upgradeValue = upgradeText.getUpgradeValue();
        updateDisplay();
    }

    @FXML
    private void handlePurchase(){
        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeValue.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeValue.getUpgradeID());

        int coin = fileManager.getGameFile().getCoins();
        if (coin >= upgradeValue.getCost(currentUpgState)){
            purchase();
            equipment.reloadData();
        }
    }

    // Private Method

    private void updateDisplay(){

        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeValue.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeValue.getUpgradeID());

        title.setText(upgradeText.getUpgText());
        progressBar.setProgress(upgradeValue.getProgress(currentUpgState));

        if (upgradeValue.isComplete(currentUpgState)){
            description.setText("Completed");
        } else{
            description.setText(" "+ upgradeValue.getValue(currentUpgState) + upgradeText.getUnit() +
                    " ➔ " +
                    upgradeValue.getValue(currentUpgState + 1) + upgradeText.getUnit());
        }

        costLabel.setText(String.valueOf(upgradeValue.getCost(currentUpgState)));
    }


    private void purchase(){

        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeValue.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeValue.getUpgradeID());

        if (upgradeValue.isComplete(currentUpgState)) return;
        int cost = upgradeValue.getCost(currentUpgState);
        int skillID = upgradeValue.getSkillID();
        fileManager.getGameFile().reduceCoins(cost);
        SkillFile file = SkillSupplier.getSkillFile(skillID, fileManager);
        file.upgrade(upgradeValue.getUpgradeID());
        updateDisplay();
    }
}
