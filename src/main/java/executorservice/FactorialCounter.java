package executorservice;

import java.util.concurrent.Callable;

/**
 * @author EAverkin
 */
public class FactorialCounter implements Callable<Integer> {

    private int factorial;

    public FactorialCounter(int factorial) {
        this.factorial = factorial;
    }

    @Override
    public Integer call() {
        return countFactorial(factorial);
    }

    private int countFactorial(int factorial) {
        if (factorial == 0 || factorial == 1) {
            return 1;
        }
        return factorial * countFactorial(factorial - 1);

    }
}
