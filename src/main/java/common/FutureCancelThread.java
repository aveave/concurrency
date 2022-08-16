package common;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureCancelThread implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        Thread.sleep(6000);

            return 3;

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> result = executor.submit(new FutureCancelThread());
        result.get();
        System.out.println("Started");
        Thread.sleep(2000);
        result.cancel(false);
        System.out.println(result.isCancelled());
        System.out.println(result.get());
        executor.shutdownNow();
    }
}
