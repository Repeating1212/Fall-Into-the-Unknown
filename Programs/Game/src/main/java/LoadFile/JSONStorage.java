package LoadFile;

import java.io.*;

import LoadFile.SkillFile.AttackFile;
import LoadFile.SkillFile.DashFile;
import LoadFile.SkillFile.DefendFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONStorage {
    private static final String GAME_PATH = "GameData/game_file.json";
    private static final String ATTACK_PATH = "GameData/attack_file.json";
    private static final String DASH_PATH = "GameData/dash_file.json";
    private static final String DEFEND_PATH = "GameData/defend_file.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Single save method
    public static <T> void save(String fileName, T data) {
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Single load method
    public static <T> T load(String fileName, Class<T> clazz) {
        File file = new File(fileName);
        if (!file.exists()) {
            try {
                return clazz.newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Failed to create instance", e);
            }
        }

        try (FileReader reader = new FileReader(file)) {
            T notNull = gson.fromJson(reader, clazz);
            if (notNull != null) return notNull;
            try {
                return clazz.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to create instance", ex);
            }
        } catch (IOException e) {
            e.printStackTrace();
            try {
                return clazz.newInstance();
            } catch (Exception ex) {
                throw new RuntimeException("Failed to create instance", ex);
            }
        }
    }

    // Convenience methods (optional)
    public static void saveGameFile(GameFile gameFile) {
        save(GAME_PATH, gameFile);
    }

    public static GameFile loadGameFile() {
        return load(GAME_PATH, GameFile.class);
    }

    public static void saveAttackFile(AttackFile attackFile) {
        save(ATTACK_PATH, attackFile);
    }

    public static AttackFile loadAttackFile() {
        return load(ATTACK_PATH, AttackFile.class);
    }

    public static void saveDashFile(DashFile dashFile) {
        save(DASH_PATH, dashFile);
    }

    public static DashFile loadDashFile() {
        return load(DASH_PATH, DashFile.class);
    }

    public static void saveDefendFile(DefendFile defendFile) {
        save(DEFEND_PATH, defendFile);
    }

    public static DefendFile loadDefendFile(){
        return load(DEFEND_PATH, DefendFile.class);
    }
}