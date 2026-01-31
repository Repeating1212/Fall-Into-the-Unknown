package Game_Data;

public class SkillData {
//    private boolean attackUnlock = false;
//    private boolean defendUnlock = false;
//    private boolean dashUnlock = false;

    private final int ATTACK_SKILL_ID = 1;
    private final int DEFEND_SKILL_ID = 2;
    private final int DASH_SKILL_ID = 3;

    private boolean attackEquip = true;
    private boolean defendEquip = false;
    private boolean dashEquip = false;

    // Skill Equipment

    public void setEquip(int skill_id){
        if (skill_id == ATTACK_SKILL_ID) attackEquip = true;
        if (skill_id == DEFEND_SKILL_ID) defendEquip = true;
        if (skill_id == DASH_SKILL_ID) dashEquip = true;
    }

    public void setUnequip(int skill_id){
        if (skill_id == ATTACK_SKILL_ID) attackEquip = false;
        if (skill_id == DEFEND_SKILL_ID) defendEquip = false;
        if (skill_id == DASH_SKILL_ID) dashEquip = false;
    }

    public boolean isEquip(int skill_id){
        if (skill_id == ATTACK_SKILL_ID) return attackEquip;
        if (skill_id == DEFEND_SKILL_ID) return defendEquip;
        if (skill_id == DASH_SKILL_ID) return dashEquip;
        else return false;
    }

    public void setAttackEquip(boolean isEquip){
        attackEquip = isEquip;
    }

    public boolean isAttackEquip(){
        return attackEquip;
    }

    public void setDefendEquip(boolean isEquip){
        defendEquip = isEquip;
    }

    public boolean isDefendEquip(){
        return defendEquip;
    }

    public void setDashEquip(boolean isEquip){
        dashEquip = isEquip;
    }

    public boolean isDashEquip(){
        return dashEquip;
    }
}
