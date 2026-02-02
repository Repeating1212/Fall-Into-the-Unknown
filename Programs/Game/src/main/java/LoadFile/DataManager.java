package LoadFile;

import LoadFile.SkillFile.AttackFile;
import LoadFile.SkillFile.DashFile;
import LoadFile.SkillFile.DefendFile;

public class DataManager {

    private static GameFile currentGameFile;
    private static AttackFile attackFile;
    private static DashFile dashFile;
    private static DefendFile defendFile;

    public static GameFile getGameFile(){
        loadFile();
        return currentGameFile;
    }

    public static AttackFile getAttackFile(){
        loadFile();
        return attackFile;
    }

    public static DashFile getDashFile(){
        loadFile();
        return dashFile;
    }

    public static DefendFile getDefendFile(){
        loadFile();
        return defendFile;
    }

    public static void saveFile(){
        JSONStorage.saveGameFile(currentGameFile);
        JSONStorage.saveAttackFile(attackFile);
        JSONStorage.saveDashFile(dashFile);
        JSONStorage.saveDefendFile(defendFile);
    }

    public static void loadFile(){
        if (currentGameFile == null){
            currentGameFile = JSONStorage.loadGameFile();
        }
        if (attackFile == null){
            attackFile = JSONStorage.loadAttackFile();
        }
        if (dashFile == null){
            dashFile = JSONStorage.loadDashFile();
        }
        if(defendFile == null){
            defendFile = JSONStorage.loadDefendFile();
        }
    }
}
