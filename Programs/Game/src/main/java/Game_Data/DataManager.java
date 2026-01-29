package Game_Data;

public class DataManager {

    private static GameData currentGameData;

    public static void resetGameData(GameData gameData) {
        currentGameData = gameData;
    }

    public static GameData getGameData(){
        return currentGameData;
    }

    public static void loadData(){
        if (currentGameData == null){
            currentGameData = JSONStorage.loadGameData();
        }
    }

    public static void saveData(){
        JSONStorage.saveGameData(currentGameData);
    }
}
