package LoadFile.SkillFile;

import Game_Data.DashConfig;

public class DashFile {
    private int cooldownUpgrades = 0;
    private int rangeUpgrades = 0;

    public int getCooldownUpgrades() {
        return cooldownUpgrades;
    }

    public int getRangeUpgrades() {
        return rangeUpgrades;
    }

    public void setCooldownUpgrades(int cooldownUpgrades) {
        if (cooldownUpgrades > 0 &&
                cooldownUpgrades < DashConfig.COOLDOWN_UPG.length){
            this.cooldownUpgrades = cooldownUpgrades;
        }
    }

    public void setRangeUpgrades(int rangeUpgrades) {
        if (rangeUpgrades > 0 &&
                rangeUpgrades < DashConfig.RANGE_UPG.length){
            this.rangeUpgrades = rangeUpgrades;
        }
    }
}

