package Game_Data;

import Level.Skills.Player.EmptySkill;
import Level.Skills.Skill;
import LoadFile.DataManager;
import javafx.scene.image.Image;

public class SkillSupplier {

    public static final int EMPTY_SKILL_ID = 0;
    public static final int ATTACK_SKILL_ID = 1;
    public static final int DEFEND_SKILL_ID = 2;
    public static final int DASH_SKILL_ID = 3;

    private static final int TOTAL_SKILL = 4;

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

    // Private Method

    private static Skill getSkill(int skillID){
        return switch (skillID) {
            case ATTACK_SKILL_ID -> AttackConfig.getAttack();
            case DASH_SKILL_ID -> DashConfig.getDash();
            case DEFEND_SKILL_ID -> DefendConfig.getDefend();
            default -> new EmptySkill();
        };
    }
}
