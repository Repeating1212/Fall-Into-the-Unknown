package EditorUI;

import Data.DataClass.UpgradeValue;
import Data.Interface.PaneInterface;
import Data.Supplier.ImageLoader;
import Data.DataClass.UpgradeText;
import Data.Supplier.SkillSupplier;
import LoadFile.SkillFile.SkillFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class StorePane extends PaneInterface {

    private final int sideLine = 480;
    private Text totalCoin;
    private Pane upgradeFlowPane;

    @FXML private Pane upgradePane;
    @FXML private Text title, description, currentUpgrade;
    @FXML private Text upgradeValue_Text, unit, upgradeType;
    @FXML private Text costLabel;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView skillView, coinView;
    @FXML private Button upgradeButton;

    private UpgradeText upgradeText;
    private UpgradeValue upgradeValue;

    public void initialize(){
        title.setText("Title");
        description.setText("Description");
        currentUpgrade.setText("0/0");
        progressBar.setProgress(1.0);
        upgradeValue_Text.setText("0");
        upgradeType.setText("-");
        unit.setText("");
        costLabel.setText("0");

        skillView.setImage(ImageLoader.ATTACK_ICON);
        coinView.setImage(ImageLoader.COIN);
    }

    public void initializeData(UpgradeText upgradeText, Text parentCoinLabel, Pane rootPane) {
        this.upgradeText = upgradeText;
        this.upgradeValue = upgradeText.getUpgradeValue();
        this.totalCoin = parentCoinLabel;
        this.upgradeFlowPane = rootPane;
        updateDisplay();
    }

    @FXML
    private void handlePurchase(){
        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeValue.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeValue.getUpgradeID());

        int coin = fileManager.getGameFile().getCoins();
        if (coin >= upgradeValue.getCost(currentUpgState)){
            purchase();
        }
    }

    // Private Method

    private void updateDisplay(){

        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeValue.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeValue.getUpgradeID());


        skillView.setImage(upgradeText.getSkillImage());
        title.setText(upgradeText.getTitle());
        description.setText(upgradeText.getDescription());
        currentUpgrade.setText(upgradeText.getUpgradeText(currentUpgState));
        progressBar.setProgress(upgradeValue.getProgress(currentUpgState));

        if (upgradeValue.isComplete(currentUpgState)){
            upgradeValue_Text.setText("Completed");
            unit.setText("");
            upgradeType.setText("");
        } else{
            double incrementValue = upgradeValue.getIncrement(currentUpgState);
            if (incrementValue > 0){
                upgradeValue_Text.setText("+" + incrementValue);
            } else {
                upgradeValue_Text.setText(String.valueOf(incrementValue));
            }
            unit.setText(upgradeText.getUnit());
            upgradeType.setText(upgradeText.getUpgText());
        }

        costLabel.setText(String.valueOf(upgradeValue.getCost(currentUpgState)));
        totalCoin.setText(String.valueOf(fileManager.getGameFile().getCoins()));
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
