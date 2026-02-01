package Game_Data;

import java.io.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONStorage {
    private static final String FILE_PATH = "game_data.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Game data
    protected static void saveGameData(GameData gameData) {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            gson.toJson(gameData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static GameData loadGameData() {
        try (FileReader reader = new FileReader(FILE_PATH)) {
            GameData loadedData = gson.fromJson(reader, GameData.class);
            if (loadedData == null) return new GameData();
            return loadedData;
        } catch (IOException e) {
            // Return new instance if file doesn't exist
            return new GameData();
        }
    }
}