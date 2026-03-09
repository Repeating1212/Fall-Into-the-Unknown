package Data.DataClass;

import java.util.ArrayList;

public class TimerValue {

    private final ArrayData<Timer> timers = new ArrayData<>();
    private final ArrayList<Double> values = new ArrayList<>();

    public void add(double duration, double value) {
        timers.add(new Timer(duration, true));
        values.add(value);
    }

    public void update(double deltaTime) {
        // Update all timers
        for (Timer timer : timers.get()) {
            timer.update(deltaTime);
        }

        // Remove ended timers and corresponding values
        ArrayList<Timer> toRemove = new ArrayList<>();
        ArrayList<Double> remainingValues = new ArrayList<>();

        for (int i = 0; i < timers.length(); i++) {
            if (!timers.get().get(i).isEnd()) {
                remainingValues.add(values.get(i));
            } else {
                toRemove.add(timers.get().get(i));
            }
        }

        timers.remove(toRemove);
        values.clear();
        values.addAll(remainingValues);
    }

    public double getSum() {
        double sum = 0;
        for (double v : values) {
            sum += v;
        }
        return sum;
    }

    public double getProduct() {
        if (values.isEmpty()) return 1;
        double product = 1;
        for (double v : values) {
            product *= v;
        }
        return product;
    }
}