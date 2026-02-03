package Game_Data.SkillData;

public class UpgradeValue {

    private final double[] value;
    private final double[] increment;
    private final double[] cost;

    public UpgradeValue(double[] value, double[] cost){
        this.value = value;
        this.cost = cost;
        this.increment = new double[value.length];
        for(int i = 0; i < value.length - 1; i++){
            increment[i] = value[i] - value[i + 1];
        }
        increment[value.length - 1] = 0;
    }

    public double getValue(int currentUpgrade) {
        return value[currentUpgrade];
    }

    public int getCost(int currentUpgrade) {
        return (int) cost[currentUpgrade];
    }

    public double getIncrement(int currentUpgrade) {
        return increment[currentUpgrade];
    }

    public int getTotalUpgrade(){
        return value.length;
    }
}
