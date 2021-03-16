package thirdsection;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FactorialThread extends Thread{

    private long inputNumber;

    private BigInteger result = BigInteger.ZERO;

    private boolean isFinished = false;

    public FactorialThread(long inputNumber) {
        this.inputNumber = inputNumber;
    }

    public void run() {
        this.result = factorial(inputNumber);
        this.isFinished = true;
    }

    public BigInteger factorial(long n) {
        BigInteger tempResult = BigInteger.ONE;

        for (long i = n; i > 0; i--) {
            tempResult = tempResult.multiply(new BigInteger(Long.toString(i)));
        }
        return tempResult;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public BigInteger getResult() {
        return result;
    }

    public static void main(String[] args) throws InterruptedException {
        List<Long> inputNumbers = Arrays.asList(0L, 3435L, 35435L, 2324L, 4656L, 23L, 5556L);

        List<FactorialThread> threads = inputNumbers.stream().map(FactorialThread::new).collect(Collectors.toList());

        threads.forEach(Thread::start);

        for (FactorialThread thread : threads) {
            thread.join();
        }

        for (int i= 0; i < inputNumbers.size(); i++) {
            if (threads.get(0).isFinished()) {
                System.out.println("Factorial for number " + inputNumbers.get(i) + " is a " + threads.get(i).getResult());
            } else {
                System.out.println("Calculation for " + inputNumbers.get(i) + " is still in progress");
            }
        }
    }

}
