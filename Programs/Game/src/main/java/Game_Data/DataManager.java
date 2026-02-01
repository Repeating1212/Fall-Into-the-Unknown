package Game_Data;

public class DataManager {

    private static GameData currentGameData;

    public static GameData getGameData(){
        if (currentGameData == null) loadData();
        return currentGameData;
    }

    public static void saveData(){
        JSONStorage.saveGameData(currentGameData);
    }

    public static void loadData(){
        if (currentGameData == null){
            currentGameData = JSONStorage.loadGameData();
        }
    }
}
