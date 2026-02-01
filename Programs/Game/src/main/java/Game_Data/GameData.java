package Game_Data;

import java.io.*;

public class GameData implements Serializable {
    private int coins = 0;
    private final int[] equipedSkill = new int[]{1, 0,0,0 };

//    private int highScore;
//    private boolean soundEnabled;
//    private String playerName;

    // Coins
    public int getCoins() {
        return coins;
    }

    public void addCoins(int increment) {
        coins += increment;
    }

    public void setEquip(int skill_id){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == 0) {
                equipedSkill[i] = skill_id;
                break;
            }
        }
    }

    public void setUnequip(int skill_id){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == skill_id)
                equipedSkill[i] = 0;
        }
    }

    public boolean isEquip(int skill_id){
        for (int j : equipedSkill) {
            if (j == skill_id) return true;
        }
        return false;
    }

    public int[] getEquipedSkill(){
        return equipedSkill;
    }

//    public int getHighScore() { return highScore; }
//    public void setHighScore(int highScore) { this.highScore = highScore; }
//
//    public boolean isSoundEnabled() { return soundEnabled; }
//    public void setSoundEnabled(boolean soundEnabled) { this.soundEnabled = soundEnabled; }
//
//    public String getPlayerName() { return playerName; }
//    public void setPlayerName(String playerName) { this.playerName = playerName; }
}