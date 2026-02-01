package Game_Data.Skill;

public class SkillData {

    private final int[] equipedSkill = new int[4];

    // Skill Equipment

    public void setEquip(int skill_id){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == 0) {
                equipedSkill[i] = skill_id;
                break;
            }
        }
    }

    public void setUnequip(int skill_id){
        for (int i = 0; i < equipedSkill.length; i++){
            if (equipedSkill[i] == skill_id)
                equipedSkill[i] = 0;
        }
    }

    public boolean isEquip(int skill_id){
        for (int j : equipedSkill) {
            if (j == skill_id) return true;
        }
        return false;
    }

    public int[] getEquipedSkill(){
        return equipedSkill;
    }

    // Private Method
}
