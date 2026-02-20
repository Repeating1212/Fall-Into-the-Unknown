package Game_File;

import Game_File.SkillFile.*;

public class FileManager {

    private GameFile currentGameFile;
    private AttackFile attackFile;
    private DashFile dashFile;
    private DefendFile defendFile;
    private SkillFile[] skillFiles;

    // Get File

    public GameFile getGameFile(){
        return currentGameFile;
    }

    public AttackFile getAttackFile(){
        return attackFile;
    }

    public DashFile getDashFile(){
        return dashFile;
    }

    public DefendFile getDefendFile(){
        return defendFile;
    }

    public SkillFile getSkillFiles(Class<? extends SkillFile> classType){
        for (SkillFile skillFile : skillFiles){
            if (skillFile.getClass() == classType){
                return skillFile;
            }
        }
        return new EmptySkillFile();
    }

    // Save & Load

    public void saveFile(){
        JSONStorage.saveGameFile(currentGameFile);
        JSONStorage.saveAttackFile(attackFile);
        JSONStorage.saveDashFile(dashFile);
        JSONStorage.saveDefendFile(defendFile);
    }

    public void loadFile(){
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
