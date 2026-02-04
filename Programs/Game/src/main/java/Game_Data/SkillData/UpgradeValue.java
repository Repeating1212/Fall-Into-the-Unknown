package Game_Data.SkillData;

import LoadFile.DataManager;

public class UpgradeValue {

    private final int skillID;
    private final int upgradeID;

    private final double[] value;
    private final double[] increment;
    private final double[] cost;

    private int currentUpgState;

    public UpgradeValue(double[] value, double[] cost, int skillID, int upgradeID){
        this.value = value;
        this.cost = cost;
        this.skillID = skillID;
        this.upgradeID = upgradeID;
        this.increment = new double[value.length - 1];
        for(int i = 0; i < value.length - 1; i++){
            increment[i] = value[i+ 1] - value[i];
        }

        reloadData();
    }

    public void reloadData(){
        this.currentUpgState = DataManager.getAttackFile().getUpgrade(upgradeID);
    }

    public double getValue(int currentUpgrade) {
        if (currentUpgrade >= increment.length) return 0;
        return value[currentUpgrade];
    }

    public int getCurrentUpgState() {
        return currentUpgState;
    }

    public int getCost() {
        return (int) cost[currentUpgState];
    }

    public double getIncrement() {
        if (currentUpgState >= increment.length) return 0;
        return increment[currentUpgState];
    }

    public double getProgress(){
        return (double)  currentUpgState / increment.length;
    }

    public int getTotalUpgrade(){
        return increment.length;
    }

    public int getSkillID(){
        return skillID;
    }

    public int getUpgradeID(){
        return upgradeID;
    }

    public boolean isComplete(){
        return (currentUpgState == increment.length);
    }

}
