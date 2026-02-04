package LoadFile;

import LoadFile.SkillFile.AttackFile;
import LoadFile.SkillFile.DashFile;
import LoadFile.SkillFile.DefendFile;
import LoadFile.SkillFile.SkillFile;

public class DataManager {

    private static GameFile currentGameFile;
    private static AttackFile attackFile;
    private static DashFile dashFile;
    private static DefendFile defendFile;
    private static SkillFile[] skillFiles;

    public static GameFile getGameFile(){
        return currentGameFile;
    }

    public static AttackFile getAttackFile(){
        return attackFile;
    }

    public static DashFile getDashFile(){
        return dashFile;
    }

    public static DefendFile getDefendFile(){
        return defendFile;
    }

    public static SkillFile[] getSkillFiles(){
        return skillFiles;
    }

    public static void saveFile(){
        JSONStorage.saveGameFile(currentGameFile);
        JSONStorage.saveAttackFile(attackFile);
        JSONStorage.saveDashFile(dashFile);
        JSONStorage.saveDefendFile(defendFile);
    }

    public static void loadFile(){
        if (attackFile == null){
            attackFile = JSONStorage.loadAttackFile();
        }
        if (dashFile == null){
            dashFile = JSONStorage.loadDashFile();
        }
        if(defendFile == null){
            defendFile = JSONStorage.loadDefendFile();
        }
        skillFiles = new SkillFile[]{
                attackFile, dashFile, defendFile
        };
        // GameFile need to load other file to run
        if (currentGameFile == null){
            currentGameFile = JSONStorage.loadGameFile();
        }
    }
}
