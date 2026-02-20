package Game_File;

import Data.Supplier.SkillSupplier;

import java.io.*;

public class GameFile implements Serializable {
    private int coins = 0;
    private int[] equipment = new int[]{1, 0, 0, 0};

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
        equipment = SkillSupplier.setEquip(skill_id, equipment);
    }

    public void setUnequip(int skill_id){
        equipment = SkillSupplier.setUnequip(skill_id, equipment);
        equipment = rearrangeList(equipment);
    }

    public boolean isEquip(int skill_id){
        return SkillSupplier.isEquip(skill_id, equipment);
    }

    public int[] getEquipment(){
        return equipment;
    }

    // Private method

    private int[] rearrangeList(int[] list) {
        int[] result = new int[list.length];
        int index = 0;

        // Move non-zeros to front
        for (int skill : list) {
            if (skill != 0) {
                result[index++] = skill;
            }
        }
        // Remaining positions will be 0 (default int value)

        return result;
    }
}