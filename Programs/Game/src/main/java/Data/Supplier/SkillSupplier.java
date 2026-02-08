package Data.Supplier;

import Data.Config.*;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;
import LoadFile.FileManager;
import LoadFile.SkillFile.EmptySkillFile;
import LoadFile.SkillFile.SkillFile;
import javafx.scene.image.Image;

public class SkillSupplier {

    private static final int TOTAL_SKILL = 4;
    private static final SkillConfigList skillConfigList = new SkillConfigList();
    private static final int EMPTY_SKILL_ID = skillConfigList.getEmptySkill().getConfigID();

    public static Skill[] getPlayerSkills(FileManager fileManager){
        int[] equipedSkill = fileManager.getGameFile().getEquipedSkill();

        Skill[] skills = new Skill[equipedSkill.length];
        for (int i = 0; i < equipedSkill.length; i++){
            skills[i] = SkillSupplier.getSkill(equipedSkill[i], fileManager);
        }
        return skills;
    }

    public static Image[] getImages_UI(FileManager fileManager){
        Image[] skillImages = ImageLoader.SKILL_ICONS_UI;
        Image[] returnImage = new Image[4];
        int[] equipedSkill = fileManager.getGameFile().getEquipedSkill();
        for (int i = 0; i < equipedSkill.length; i++) {
            for (int j = 0; j < TOTAL_SKILL; j ++){
                if(equipedSkill[i] == j){
                    returnImage[i] = skillImages[j];
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
        Image[] skillImages = ImageLoader.SKILL_ICONS_LEVEL;
        Image[] returnImage = new Image[4];
        int[] equipedSkill = fileManager.getGameFile().getEquipedSkill();
        for (int i = 0; i < equipedSkill.length; i++) {
            for (int j = 0; j < TOTAL_SKILL; j ++){
                if(equipedSkill[i] == j){
                    returnImage[i] = skillImages[j];
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

    public static int getEmptySkillId(){
        return EMPTY_SKILL_ID;
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
