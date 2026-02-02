package LoadFile.SkillFile;

import Game_Data.DefendConfig;

public class DefendFile {
    private int durationUpgrade = 0;
    private int cooldownUpgrades = 0;

    public int getCooldownUpgrades() {
        return cooldownUpgrades;
    }

    public int getDurationUpgrade() {
        return durationUpgrade;
    }

    public void setCooldownUpgrades(int cooldownUpgrades) {
        if (cooldownUpgrades > 0 &&
                cooldownUpgrades < DefendConfig.COOLDOWN_UPG.length){
            this.cooldownUpgrades = cooldownUpgrades;
        }
    }

    public void setDurationUpgrade(int durationUpgrade) {
        if (durationUpgrade > 0 &&
                durationUpgrade < DefendConfig.DURATION_UPG.length){
            this.durationUpgrade = durationUpgrade;
        }
    }
}
