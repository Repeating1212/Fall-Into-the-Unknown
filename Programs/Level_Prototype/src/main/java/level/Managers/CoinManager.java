package level.Managers;

import level.Data.Properties.Position;
import level.Data.Properties.Property;
import level.Objects.Concrete_Class.Coin;

import java.util.ArrayList;

public class CoinManager {

    private final int COIN_NUM = 5;
    private int moneyValue = 0;
    private final ArrayList<Coin> coins = new ArrayList<>();

    private final Level level;

    public CoinManager(Level level){
        this.level = level;
    }

    public ArrayList<Coin> spawnCoins(Property property, Observer observer){
        coins.clear();
        for (int i = 0; i < COIN_NUM; i++) {
            coins.add(new Coin(property, observer));
        }
        return coins;
    }

    public int getMoneyValue() {
        return moneyValue;
    }

    public void handleCoinCollection(){
        ArrayList<Coin> collectedCoins = new ArrayList<>();
        for (Coin coin : coins){
            if (coin.isCollected()){
                collectedCoins.add(coin);
            }
        }
        coins.removeAll(collectedCoins);
        level.removeObjects(new ArrayList<>(collectedCoins));
        level.removeDisplay(new ArrayList<>(collectedCoins));
        moneyValue += collectedCoins.size();
    }
}
