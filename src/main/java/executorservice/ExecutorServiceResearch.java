package executorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author EAverkin
 */
public class ExecutorServiceResearch {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        int[] array = {5, 2, 5, 3, 1, 10};
        for (int i : array) {
            Future<Integer> future = executorService.submit(new FactorialCounter(i));
            try {
                System.out.println(future.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
