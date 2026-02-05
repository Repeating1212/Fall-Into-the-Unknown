package Data.DataClass;

import javafx.scene.image.Image;

public class UpgradeText {
    private final String title;
    private final String description;
    private final String upgradeText;
    private final String unit;

    private final Image skillImage;
    private final UpgradeValue upgradeValue;

    public UpgradeText(String title, String description, String upgradeText,
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

    public UpgradeValue getUpgradeValue() {
        return upgradeValue;
    }

    public String getUpgradeText(int currentUpgState) {
        return currentUpgState + "/" + upgradeValue.getTotalUpgrade();
    }
}
