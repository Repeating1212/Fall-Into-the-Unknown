package Game_UI.Store;

import Game_Data.ImageLoader;
import Game_Data.SkillData.UpgradeData;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class UpgradesController {

    private final int sideLine = 480;

    @FXML private Text title, description, currentUpgrade;
    @FXML private Text upgradeValue, unit, upgradeType;
    @FXML private Text coinLabel;
    @FXML private ProgressBar progressBar;
    @FXML private ImageView skillView, coinView;

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

    public void initializeData(UpgradeData upgradeData) {

        skillView.setImage(upgradeData.getSkillImage());
        title.setText(upgradeData.getTitle());
        description.setText(upgradeData.getDescription());
        currentUpgrade.setText( upgradeData.getCurrentUpgradeID() + "/" + upgradeData.getTotalUpgrade());
        progressBar.setProgress(upgradeData.getProgress());

        double incrementValue = upgradeData.getNextUpg();
        if (incrementValue > 0){
            upgradeValue.setText("+" + incrementValue);
        } else {
            upgradeValue.setText(String.valueOf(incrementValue));
        }
        unit.setText(upgradeData.getUnit());
        upgradeType.setText(upgradeData.getUpgText());

        coinLabel.setText(String.valueOf(upgradeData.getCost()));
    }
}
