package Game_Data;

import java.io.*;

public class GameData implements Serializable {
    private int coins = 0;

//    private int highScore;
//    private boolean soundEnabled;
//    private String playerName;

    // Getters and setters
    public int getCoins() { return coins; }

    public void addCoins(int increment) {
        coins += increment;
    }

    public void setCoins(int coins) { this.coins = coins; }

//    public int getHighScore() { return highScore; }
//    public void setHighScore(int highScore) { this.highScore = highScore; }
//
//    public boolean isSoundEnabled() { return soundEnabled; }
//    public void setSoundEnabled(boolean soundEnabled) { this.soundEnabled = soundEnabled; }
//
//    public String getPlayerName() { return playerName; }
//    public void setPlayerName(String playerName) { this.playerName = playerName; }
}