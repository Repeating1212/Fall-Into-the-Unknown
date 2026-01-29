package Level.Skills;

public class Timer {

    private double currentTime = 0;
    private final double COOLDOWN;
    private boolean pendingStart = false;

    public Timer(double cooldown){
        this.COOLDOWN = cooldown;
    }

    public void update(double deltaTime){
        currentTime = (currentTime > deltaTime) ? (currentTime - deltaTime) : 0;
    }

    public void setPending(){
        pendingStart = true;
    }

    public boolean isPending(){
        return pendingStart;
    }

    public void start(){
        currentTime = COOLDOWN;
        pendingStart = false;
    }

    public boolean isEnd(){
        return (currentTime == 0);
    }

    public boolean isTicking(){
        return (currentTime > 0);
    }

    public boolean isDeactive(){
        return (!pendingStart && currentTime == 0);
    }

    public double getCooldownPercentage(){
        return (currentTime / COOLDOWN);
    }
}
