package Level.BaseLevel.Properties;

public class Health {
    private int currentHealth;
    private final int MAXIMUM_HEALTH;
    private final Status status;

    public Health(int maximumHealth, Status status){
        MAXIMUM_HEALTH = maximumHealth;
        currentHealth = MAXIMUM_HEALTH;
        this.status = status;
    }

    protected void reduceHealth(double damage) {
        currentHealth -= (int) damage;
        if (currentHealth < 0) currentHealth = 0;
    }

    protected void increaseHealth(double increment){
        currentHealth -= (int) increment;
        if (currentHealth > MAXIMUM_HEALTH) currentHealth = MAXIMUM_HEALTH;
    }

    // Getter
    protected int getHealth() {
        return currentHealth;
    }

    protected double getHealthPercentage(){
        return ((double) currentHealth / MAXIMUM_HEALTH);
    }

    protected boolean isAlive(){
        return (currentHealth > 0);
    }

    protected boolean isDead(){
        return (currentHealth == 0);
    }

}
