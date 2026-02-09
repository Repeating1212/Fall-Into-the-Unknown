package Data.DataClass;

public class UpgradeValue {

    private int skillID;
    private final int upgradeID;

    private final double[] value;
    private final double[] increment;
    private final double[] cost;

    public UpgradeValue(double[] value, double[] cost, int upgradeID){
        this.value = initializeValue(value);
        this.cost = initializeCost(cost, this.value);
        this.increment = initializeIncrement(this.value);
        this.upgradeID = upgradeID;
    }

    public void setSkillID(int skillID){
        this.skillID = skillID;
    }

    public double getValue(int currentUpgrade) {
        if (currentUpgrade >= value.length) return 0;
        return value[currentUpgrade];
    }

    public int getCost(int currentUpgState) {
        if (currentUpgState >= cost.length) return 0;
        return (int) cost[currentUpgState];
    }

    public double getIncrement(int currentUpgState) {
        if (currentUpgState >= increment.length) return 0;
        return increment[currentUpgState];
    }

    public double getProgress(int currentUpgState){
        if (increment.length == 0) return 1.0;
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

    public boolean isComplete(int currentUpgState){
        return (currentUpgState == increment.length);
    }

    // Private Method

    private double[] initializeValue(double[] value){
        if (value == null) return new double[]{0};
        if (value.length < 1) return new double[]{0};

        return value;
    }

    private double[] initializeCost(double[] cost, double[] value){
        if (cost == null) return new double[]{0};
        if (cost.length < 1) return new double[]{0};
        if (cost.length != value.length) return new double[value.length];

        return cost;
    }

    private double[] initializeIncrement(double[] value){
        if (value.length == 0) return new double[]{};

        double[] increment = new double[value.length - 1];
        for(int i = 0; i < increment.length; i++){
            increment[i] = value[i + 1] - value[i];
        }
        return increment;
    }
}
