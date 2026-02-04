package Game_Data.Supplier;

import Game_Data.Config.*;
import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;
import LoadFile.DataManager;
import LoadFile.SkillFile.EmptySkillFile;
import LoadFile.SkillFile.SkillFile;
import javafx.scene.image.Image;

public class SkillSupplier {

    private static final int TOTAL_SKILL = 4;
    private static final SkillConfig skillConfig = new SkillConfig();

    public static Skill[] getPlayerSkills(){
        int[] equipedSkill = DataManager.getGameFile().getEquipedSkill();

        Skill[] skills = new Skill[equipedSkill.length];
        for (int i = 0; i < equipedSkill.length; i++){
            skills[i] = SkillSupplier.getSkill(equipedSkill[i]);
        }
        return skills;
    }

    public static Image[] getImages_UI(){
        Image[] skillImages = ImageLoader.SKILL_ICONS_UI;
        Image[] returnImage = new Image[4];
        int[] equipedSkill = DataManager.getGameFile().getEquipedSkill();
        for (int i = 0; i < equipedSkill.length; i++) {
            for (int j = 0; j < TOTAL_SKILL; j ++){
                if(equipedSkill[i] == j){
                    returnImage[i] = skillImages[j];
                }
            }
        }
        return returnImage;
    }

    public static Image[] getImages_Level(){
        Image[] skillImages = ImageLoader.SKILL_ICONS_LEVEL;
        Image[] returnImage = new Image[4];
        int[] equipedSkill = DataManager.getGameFile().getEquipedSkill();
        for (int i = 0; i < equipedSkill.length; i++) {
            for (int j = 0; j < TOTAL_SKILL; j ++){
                if(equipedSkill[i] == j){
                    returnImage[i] = skillImages[j];
                }
            }
        }
        return returnImage;
    }

    public static SkillFile getSkillFile(int skillID) {
        return switch (skillID) {
            case SkillConfig.ATTACK_SKILL_ID -> DataManager.getAttackFile();
            default -> new EmptySkillFile();
        };
    }

    // GameFile Method

    public static int[] setEquip(int skill_id, int[] equipedSkill){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == SkillConfig.EMPTY_SKILL_ID) {
                equipedSkill[i] = skill_id;
                break;
            }
        }
        return equipedSkill;
    }

    public static int[] setUnequip(int skill_id, int[] equipedSkill){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == skill_id)
                equipedSkill[i] = SkillConfig.EMPTY_SKILL_ID;
        }
        return equipedSkill;
    }

    public static boolean isEquip(int skill_id, int[] equipedSkill){
        for (int j : equipedSkill) {
            if (j == skill_id) return true;
        }
        return false;
    }

    public static SkillConfig_Interface[] getSkillsConfig(){
        return skillConfig.getSkillsConfig();
    }

    public static int[] getInitialSkill(){
        return skillConfig.initialSkill();
    }


    // Private Method

    private static Skill getSkill(int skillID){
        for (SkillConfig_Interface skillConfig : skillConfig.getSkillsConfig()){
            if (skillConfig.getConfigID() == skillID){
                return skillConfig.getSkill();
            }
        }
        return new EmptySkill();
    }
}
