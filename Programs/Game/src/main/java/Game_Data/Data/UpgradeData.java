package Game_Data.Data;

import javafx.scene.image.Image;

public class UpgradeData {
    private final String title;
    private final String description;
    private final String upgradeText;
    private final String unit;

    private final Image skillImage;
    private final UpgradeValue upgradeValue;

    public UpgradeData(String title, String description, String upgradeText,
                       String  unit, Image skillImage,
                       UpgradeValue updateValue){
        this.title = title;
        this.description = description;
        this.upgradeText = upgradeText;
        this.unit = unit;
        this.skillImage = skillImage;
        this.upgradeValue = updateValue;
    }

    public Image getSkillImage() {
        return skillImage;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUpgText() {return upgradeText;}

    public String getUnit() {
        return unit;
    }

    // Middle Man

    public double getNextUpg(int currentUpgState) {
        return upgradeValue.getIncrement(currentUpgState);
    }

    public double getProgress(int currentUpgState){
        return upgradeValue.getProgress(currentUpgState);
    }

    public int getCost(int currentUpgState){
        return upgradeValue.getCost(currentUpgState);
    }

    public int getTotalUpgrade(){
        return upgradeValue.getTotalUpgrade();
    }

    public int getSkillID(){
        return upgradeValue.getSkillID();
    }

    public int getUpgradeID(){
        return upgradeValue.getUpgradeID();
    }

    public boolean isComplete(int currentUpgState){
        return upgradeValue.isComplete(currentUpgState);
    }

    public String getUpgradeText(int currentUpgState) {
        return currentUpgState + "/" + upgradeValue.getTotalUpgrade();
    }
}
