package common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

public class CustomRunnable implements Callable<Integer> {

    private static FutureTask<Integer> futureTask = new FutureTask<>(new CustomRunnable());

    @Override
    public Integer call() {
        while (!futureTask.isCancelled()) {
            System.out.println("Inside callable");
        }
        return 10;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Thread thread = new Thread(futureTask);
        thread.start();
        sleep(5000);
        futureTask.cancel(true);
//        System.out.println(futureTask.get());
        System.out.println(futureTask.isCancelled());
    }
}
