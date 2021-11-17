package executorservice;

import java.math.BigInteger;
import java.util.concurrent.RecursiveTask;

/**
 * @author EAverkin
 */
public class FactorialTask extends RecursiveTask<BigInteger> {

    private int start = 1;
    private int n;
    private static final int THRESHOLD = 20;

    public static void main(String[] args) {

    }

    @Override
    protected BigInteger compute() {
        return null;
    }
}
