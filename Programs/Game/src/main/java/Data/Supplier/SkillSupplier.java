package Data.Supplier;

import Data.Config.*;
import Level.Player.Skills.Player.EmptySkill;
import Level.Player.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.EmptySkillFile;
import LoadFile.SkillFile.SkillFile;
import javafx.scene.image.Image;

public class SkillSupplier {

    private static final SkillConfigList skillConfigList = new SkillConfigList();

    public static Skill[] getPlayerSkills(FileManager fileManager){
        int[] equipment = fileManager.getGameFile().getEquipment();

        Skill[] skills = new Skill[equipment.length];
        for (int i = 0; i < equipment.length; i++){
            skills[i] = SkillSupplier.getSkill(equipment[i], fileManager);
        }
        return skills;
    }

    public static Image[] getImages_UI(FileManager fileManager){
        Image[] returnImage = new Image[4];
        int[] equipment = fileManager.getGameFile().getEquipment();

        for (int i = 0; i < equipment.length; i ++){
            for (SkillConfig skillConfig : skillConfigList.getSkillsConfig()){
                if (skillConfig.getConfigID() == equipment[i]){
                    returnImage[i] = skillConfig.UI_IMAGE;
                }
            }
        }

        return returnImage;
    }

    public static SkillConfig getSkillConfig(int skillID){
        for (SkillConfig skillConfig : skillConfigList.getSkillsConfig()){
            if (skillConfig.getConfigID() == skillID){
                return skillConfig;
            }
        }
        return skillConfigList.getEmptySkill();
    }


    public static Image[] getImages_Level(FileManager fileManager){
        Image[] returnImage = new Image[4];
        int[] equipment = fileManager.getGameFile().getEquipment();

        for (int i = 0; i < equipment.length; i ++){
            for (SkillConfig skillConfig : skillConfigList.getSkillsConfig()){
                if (skillConfig.getConfigID() == equipment[i]){
                    returnImage[i] = skillConfig.LEVEL_IMAGE;
                }
            }
        }

        return returnImage;
    }

    public static SkillFile getSkillFile(int skillID, FileManager fileManager) {
        for (SkillConfig skillConfig : skillConfigList.getSkillsConfig()){
            if(skillConfig.getConfigID() == skillID){
                return fileManager.getSkillFiles(skillConfig.getFileType());
            }
        }

        return new EmptySkillFile();
    }

    public static SkillConfig[] getSkillsConfig(){
        return skillConfigList.getSkillsConfig();
    }

    // GameFile Method

    public static int[] setEquip(int skill_id, int[] equipedSkill){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == skillConfigList.getEmptySkill().getConfigID()) {
                equipedSkill[i] = skill_id;
                break;
            }
        }
        return equipedSkill;
    }

    public static int[] setUnequip(int skill_id, int[] equipedSkill){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == skill_id)
                equipedSkill[i] = skillConfigList.getEmptySkill().getConfigID();
        }
        return equipedSkill;
    }

    public static boolean isEquip(int skill_id, int[] equipedSkill){
        for (int j : equipedSkill) {
            if (j == skill_id) return true;
        }
        return false;
    }


    // Private Method

    private static Skill getSkill(int skillID, FileManager fileManager){
        for (SkillConfig skillConfig : skillConfigList.getSkillsConfig()){
            if (skillConfig.getConfigID() == skillID){
                return skillConfig.getSkill(fileManager);
            }
        }
        return new EmptySkill();
    }
}
