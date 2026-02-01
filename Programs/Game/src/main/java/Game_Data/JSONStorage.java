package Game_Data;

import java.io.*;

import Game_Data.Skill.SkillData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONStorage {
    private static final String FILE_PATH = "game_data.json";
    private static final String SKILL_FILE_PATH = "skill_data.json";
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

    // Skill Data

    protected static SkillData loadSkillData() {
        try (FileReader reader = new FileReader(SKILL_FILE_PATH)) {
            SkillData loadedData = gson.fromJson(reader, SkillData.class);
            if (loadedData == null) return new SkillData();
            return loadedData;
        } catch (IOException e) {
            // Return new instance if file doesn't exist
            return new SkillData();
        }
    }

    protected static void saveSkillData(SkillData skillData) {
        try (FileWriter writer = new FileWriter(SKILL_FILE_PATH)) {
            gson.toJson(skillData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}