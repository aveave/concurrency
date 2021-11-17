package executorservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author EAverkin
 */
public class MyCallable implements Callable<Integer> {

    ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public Integer call() throws InterruptedException {
        threadLocal.set(new Random().nextInt());
        Thread.sleep(1000);
        return threadLocal.get();
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        Future<Integer> future = executorService.submit(new MyCallable());

        System.out.println(future.get());
        executorService.shutdown();
    }
}
