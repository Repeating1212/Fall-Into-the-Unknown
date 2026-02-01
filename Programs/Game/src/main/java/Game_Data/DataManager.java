package Game_Data;

import Game_Data.Skill.SkillData;

public class DataManager {

    private static GameData currentGameData;
    private static SkillData skillData;

    public static GameData getGameData(){
        if (currentGameData == null) loadData();
        return currentGameData;
    }

    public static void saveData(){
        JSONStorage.saveSkillData(skillData);
        JSONStorage.saveGameData(currentGameData);
    }

    public static void loadData(){
        if (currentGameData == null){
            currentGameData = JSONStorage.loadGameData();
        }
        if (skillData == null){
            skillData = JSONStorage.loadSkillData();
        }
    }

    public static SkillData getSkillData(){
        return skillData;
    }
}
