package Game_Data;

public class SkillData {
//    private boolean attackUnlock = false;
//    private boolean defendUnlock = false;
//    private boolean dashUnlock = false;

    private boolean attackEquip = true;
    private boolean defendEquip = false;
    private boolean dashEquip = false;

    // Skill Equipment

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
