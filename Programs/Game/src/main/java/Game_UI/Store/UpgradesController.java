package Game_UI.Store;

import Game_Data.Interface.PaneInterface;
import Game_Data.Supplier.ImageLoader;
import Game_Data.Data.UpgradeData;
import Game_Data.Supplier.SkillSupplier;
import LoadFile.SkillFile.SkillFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpgradesController extends PaneInterface {

    private final int sideLine = 480;
    private Text parentCoinLabel;
    private Pane upgradeFlowPane;

    @FXML private Pane upgradePane;
    @FXML private Text title, description, currentUpgrade;
    @FXML private Text upgradeValue, unit, upgradeType;
    @FXML private Text coinLabel;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView skillView, coinView;
    @FXML private Button upgradeButton;

    private UpgradeData upgradeData;

    public void initialize(){
        title.setText("Attack");
        description.setText("Sample description");
        currentUpgrade.setText("1/4");
        progressBar.setProgress(0);
        upgradeValue.setText("0");
        upgradeType.setText("Empty Type");
        unit.setText("");
        coinLabel.setText("0");

        skillView.setImage(ImageLoader.ATTACK_ICON);
        coinView.setImage(ImageLoader.COIN);
    }

    public void initializeData(UpgradeData upgradeData, Text parentCoinLabel, Pane rootPane) {
        this.upgradeData = upgradeData;
        this.parentCoinLabel = parentCoinLabel;
        this.upgradeFlowPane = rootPane;
        updateDisplay();
    }

    @FXML
    private void handlePurchase(){
        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeData.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeData.getUpgradeID());

        int coin = fileManager.getGameFile().getCoins();
        if (coin >= upgradeData.getCost(currentUpgState)){
            purchase();
        }
    }

    // Private Method

    private void updateDisplay(){

        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeData.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeData.getUpgradeID());

        skillView.setImage(upgradeData.getSkillImage());
        title.setText(upgradeData.getTitle());
        description.setText(upgradeData.getDescription());
        currentUpgrade.setText(upgradeData.getUpgradeText(currentUpgState));
        progressBar.setProgress(upgradeData.getProgress(currentUpgState));

        if (upgradeData.isComplete(currentUpgState)){
            upgradeValue.setText("Completed");
            unit.setText("");
            upgradeType.setText("");
        } else{
            double incrementValue = upgradeData.getNextUpg(currentUpgState);
            if (incrementValue > 0){
                upgradeValue.setText("+" + incrementValue);
            } else {
                upgradeValue.setText(String.valueOf(incrementValue));
            }
            unit.setText(upgradeData.getUnit());
            upgradeType.setText(upgradeData.getUpgText());
        }

        coinLabel.setText(String.valueOf(upgradeData.getCost(currentUpgState)));
        parentCoinLabel.setText(String.valueOf(fileManager.getGameFile().getCoins()));
    }


    private void purchase(){

        SkillFile skillFile = SkillSupplier.getSkillFile(upgradeData.getSkillID(), fileManager);
        int currentUpgState = skillFile.getUpgrade(upgradeData.getUpgradeID());

        if (upgradeData.isComplete(currentUpgState)) return;
        int cost = upgradeData.getCost(currentUpgState);
        int skillID = upgradeData.getSkillID();
        fileManager.getGameFile().reduceCoins(cost);
        System.out.println(upgradeData.getSkillID());
        System.out.println(upgradeData.getTitle());
        SkillFile file = SkillSupplier.getSkillFile(skillID, fileManager);
        file.upgrade(upgradeData.getUpgradeID());
        updateDisplay();
    }
}
