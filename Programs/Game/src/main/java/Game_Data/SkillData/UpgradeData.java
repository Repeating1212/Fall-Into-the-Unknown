package Game_Data.SkillData;

import javafx.scene.image.Image;

public class UpgradeData {
    private final String title;
    private final String description;
    private final String upgradeText;
    private final String unit;

    private final Image skillImage;
    private final UpgradeValue upgradeValue;
    private final int currentUpgradeID;

    public UpgradeData(String title, String description, String upgradeText,
                       String  unit, Image skillImage,
                       UpgradeValue updateValue, int currentUpgradeID){
        this.title = title;
        this.description = description;
        this.upgradeText = upgradeText;
        this.unit = unit;
        this.skillImage = skillImage;
        this.upgradeValue = updateValue;
        this.currentUpgradeID = currentUpgradeID;
    }

    public double getNextUpg() {
        return upgradeValue.getIncrement(currentUpgradeID);
    }

    public double getProgress(){
        return (double) currentUpgradeID / upgradeValue.getTotalUpgrade();
    }

    public int getCurrentUpgradeID() {
        return currentUpgradeID;
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

    public int getTotalUpgrade(){
        return upgradeValue.getTotalUpgrade();
    }

    public int getCost(){
        return upgradeValue.getCost(currentUpgradeID);
    }
}
