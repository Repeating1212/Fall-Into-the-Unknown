package LoadFile;

import Game_Data.Supplier.SkillSupplier;

import java.io.*;

public class GameFile implements Serializable {
    private int coins = 0;
    private int[] equipedSkill = SkillSupplier.getInitialSkill();

    // Coins
    public int getCoins() {
        return coins;
    }

    public void addCoins(int increment) {
        coins += increment;
    }

    public void reduceCoins(int decrement) {
        coins -= decrement;
    }

    // Equips skill

    public void setEquip(int skill_id){
        equipedSkill = SkillSupplier.setEquip(skill_id, equipedSkill);
    }

    public void setUnequip(int skill_id){
        equipedSkill = SkillSupplier.setUnequip(skill_id, equipedSkill);
    }

    public boolean isEquip(int skill_id){
        return SkillSupplier.isEquip(skill_id, equipedSkill);
    }

    public int[] getEquipedSkill(){
        return equipedSkill;
    }

    public int getSkillLength(){ return equipedSkill.length;}
}