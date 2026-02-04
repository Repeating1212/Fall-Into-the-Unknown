package Game_UI.Store;

import Game_Data.Supplier.ImageLoader;
import Game_Data.Data.UpgradeData;
import Game_Data.Supplier.SkillSupplier;
import LoadFile.DataManager;
import LoadFile.SkillFile.SkillFile;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class UpgradesController {

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
        reloadData();
    }

    @FXML
    private void handlePurchase(){
        int coin = DataManager.getGameFile().getCoins();
        if (coin >= upgradeData.getCost()){
            purchase();
        }
    }

    // Private Method

    private void reloadData(){
        upgradeData.reloadData();

        skillView.setImage(upgradeData.getSkillImage());
        title.setText(upgradeData.getTitle());
        description.setText(upgradeData.getDescription());
        currentUpgrade.setText( upgradeData.getUpgradeState() + "/" + upgradeData.getTotalUpgrade());
        progressBar.setProgress(upgradeData.getProgress());

        if (upgradeData.isComplete()){
            upgradeValue.setText("Completed");
            unit.setText("");
            upgradeType.setText("");
        } else{
            double incrementValue = upgradeData.getNextUpg();
            if (incrementValue > 0){
                upgradeValue.setText("+" + incrementValue);
            } else {
                upgradeValue.setText(String.valueOf(incrementValue));
            }
            unit.setText(upgradeData.getUnit());
            upgradeType.setText(upgradeData.getUpgText());
        }

        coinLabel.setText(String.valueOf(upgradeData.getCost()));
        parentCoinLabel.setText(String.valueOf(DataManager.getGameFile().getCoins()));
    }


    private void purchase(){
        if (upgradeData.isComplete()) return;
        int cost = upgradeData.getCost();
        int skillID = upgradeData.getSkillID();
        DataManager.getGameFile().reduceCoins(cost);
        System.out.println(upgradeData.getSkillID());
        System.out.println(upgradeData.getTitle());
        SkillFile file = SkillSupplier.getSkillFile(skillID);
        file.upgrade(upgradeData.getUpgradeID());
        reloadData();
    }
}
