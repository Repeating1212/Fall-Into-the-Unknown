package LoadFile.SkillFile;

import Game_Data.AttackConfig;

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

    public void setAreaAngleUpgrade(int areaAngleUpgrade) {
        if (areaAngleUpgrade > 0 &&
                areaAngleUpgrade < AttackConfig.angleUpgLength() ){
            this.areaAngleUpgrade = areaAngleUpgrade;
        }
    }

    public void setCooldownUpgrade(int cooldownUpgrade) {
        if (cooldownUpgrade > 0 &&
                cooldownUpgrade < AttackConfig.cooldownUpgLength()){
            this.cooldownUpgrade = cooldownUpgrade;
        }
    }

    public void setDamageUpgrade(int damageUpgrade) {
        if (damageUpgrade > 0 &&
                damageUpgrade < AttackConfig.damageUpgLength()){
            this.damageUpgrade = damageUpgrade;
        }
    }

    public void setRangeUpgrade(int rangeUpgrade) {
        if (rangeUpgrade > 0 &&
                rangeUpgrade < AttackConfig.rangeUpgLength()){
            this.rangeUpgrade = rangeUpgrade;
        }
    }
}
