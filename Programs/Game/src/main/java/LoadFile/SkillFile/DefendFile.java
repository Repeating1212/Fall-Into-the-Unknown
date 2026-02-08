package LoadFile.SkillFile;

import Data.Config.DefendConfig;

public class DefendFile implements SkillFile{
    private int durationUpgrade = 0;
    private int cooldownUpgrades = 0;

    public int getCooldownUpgrades() {
        return cooldownUpgrades;
    }

    public int getDurationUpgrade() {
        return durationUpgrade;
    }

    public void upgrade(int upgradeID){
        switch(upgradeID){
            case DefendConfig.DURATION_ID -> durationUpgrade ++;
            case DefendConfig.COOLDOWN_ID -> cooldownUpgrades ++;
        }
    }

    public int getUpgrade(int upgradeID){
        return switch (upgradeID){
            case DefendConfig.COOLDOWN_ID -> cooldownUpgrades;
            case DefendConfig.DURATION_ID -> durationUpgrade;
            default -> 0;
        };
    }
}
