package LoadFile.SkillFile;

import Game_Data.Config.AttackConfig;
import Game_Data.Config.DashConfig;
import Game_Data.Config.SkillConfig;

public class DashFile implements SkillFile {
    private int cooldownUpgrades = 0;
    private int rangeUpgrades = 0;

    public int getCooldownUpgrades() {
        return cooldownUpgrades;
    }

    public int getRangeUpgrades() {
        return rangeUpgrades;
    }

    public void upgrade(int upgradeID){
        switch(upgradeID){
            case DashConfig.RANGE_ID -> rangeUpgrades ++;
            case DashConfig.COOLDOWN_ID -> cooldownUpgrades ++;
        }
    }

    public int getUpgrade(int upgradeID){
        return switch (upgradeID){
            case DashConfig.COOLDOWN_ID -> cooldownUpgrades;
            case DashConfig.RANGE_ID -> rangeUpgrades;
            default -> 0;
        };
    }
}

