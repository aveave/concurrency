package challenges;

public class MinMaxMetrics {

    private int count;

    private volatile double sum;
    private volatile double average;

    private volatile long min;
    private volatile long max;

    public MinMaxMetrics() {

    }

    public void addSample(long newSample) {
        synchronized (this) {
            sum = count * average;
            count++;
            average = (sum + newSample) / count;
        }
        if (newSample > max) {
            max = newSample;
        }
        if (newSample < min) {
            min = newSample;
        }
    }

    public long getMin() {
        return  min;
    }

    public long getMax() {
        return max;
    }
}
