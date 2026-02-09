package Lvl_Sample.Data.Properties;

public enum Direction {
    NONE(0, 0),
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0),
    UP_LEFT(-Math.sqrt(2)/2, -Math.sqrt(2)/2),    // -0.707, -0.707
    UP_RIGHT(Math.sqrt(2)/2, -Math.sqrt(2)/2),    //  0.707, -0.707
    DOWN_LEFT(-Math.sqrt(2)/2, Math.sqrt(2)/2),   // -0.707,  0.707
    DOWN_RIGHT(Math.sqrt(2)/2, Math.sqrt(2)/2);   //  0.707,  0.707

    private final double xMultiplier;
    private final double yMultiplier;

    Direction(double xMultiplier, double yMultiplier) {
        this.xMultiplier = xMultiplier;
        this.yMultiplier = yMultiplier;
    }

    public double getXMultiplier() {
        return xMultiplier;
    }

    public double getYMultiplier() {
        return yMultiplier;
    }

    // Helper method to get direction from key presses
    public static Direction fromKeys(boolean up, boolean down, boolean left, boolean right) {
        if (up && !down && !left && !right) return UP;
        if (!up && down && !left && !right) return DOWN;
        if (!up && !down && left && !right) return LEFT;
        if (!up && !down && !left && right) return RIGHT;
        if (up && !down && left && !right) return UP_LEFT;
        if (up && !down && !left && right) return UP_RIGHT;
        if (!up && down && left && !right) return DOWN_LEFT;
        if (!up && down && !left && right) return DOWN_RIGHT;
        return NONE;
    }
}
