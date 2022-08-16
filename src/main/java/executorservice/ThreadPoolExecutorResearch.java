package executorservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author EAverkin
 */
public class ThreadPoolExecutorResearch {

    public static void main(String[] args) {
        testCustomExecutor();
    }

    public static void testCustomExecutor() {
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, queue);

        for (int i = 0; i < 100; i++) {
            System.out.println("Start " + i);
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(123);
            });
        }
        System.out.println("The end");
        threadPoolExecutor.shutdown();
    }
}
