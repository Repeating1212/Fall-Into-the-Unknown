package Data.DataClass;

public class Timer {

    private double currentTime = 0;
    private final double DURATION;
    private boolean pendingStart = false;

    public Timer(double cooldown){
        this.DURATION = cooldown;
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
        currentTime = DURATION;
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
        return (currentTime / DURATION);
    }

    public double getDuration(){ return DURATION;}
}
