package Level.BaseLevel.Properties;

public class Status {

    private double speedPercentage = 1;
    private double defendPercentage = 1;
    private double attackPercentage = 1;

    public void increaseSpeed(int percentage){
        speedPercentage *= percentage;
    }

    public void increaseDefend(int percentage){
        defendPercentage *= percentage;
    }

    public void increaseAttack(int percentage){
        attackPercentage *= percentage;
    }

    public double getAttackPercentage() {
        return attackPercentage;
    }

    public double getDefendPercentage() {
        return defendPercentage;
    }

    public void setSpeedPercentage(double speedPercentage) {
        this.speedPercentage = speedPercentage;
    }
}
