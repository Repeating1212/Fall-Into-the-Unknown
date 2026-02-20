package Game_File.SkillFile;

import Data.SkillConfig.AttackConfig;

public class AttackFile implements SkillFile {

    private int damageUpgrade = 0;
    private int rangeUpgrade = 0;
    private int cooldownUpgrade = 0;
    private int areaAngleUpgrade = 0;

    public int getAreaAngleUpgrade() {
        return areaAngleUpgrade;
    }

    public int getCooldownUpgrade() {
        return cooldownUpgrade;
    }

    public int getDamageUpgrade() {
        return damageUpgrade;
    }

    public int getRangeUpgrade() {
        return rangeUpgrade;
    }

    public void upgrade(int upgradeID){
        switch(upgradeID){
            case AttackConfig.DAMAGE_ID -> damageUpgrade ++;
            case AttackConfig.RANGE_ID -> rangeUpgrade ++;
            case AttackConfig.COOLDOWN_ID -> cooldownUpgrade ++;
            case AttackConfig.ATTACK_ANGLE_ID -> areaAngleUpgrade ++;
        }
    }

    public int getUpgrade(int upgradeID){
        return switch (upgradeID){
            case AttackConfig.DAMAGE_ID -> damageUpgrade;
            case AttackConfig.RANGE_ID -> rangeUpgrade;
            case AttackConfig.COOLDOWN_ID -> cooldownUpgrade;
            case AttackConfig.ATTACK_ANGLE_ID -> areaAngleUpgrade;
            default -> 0;
        };
    }
}
