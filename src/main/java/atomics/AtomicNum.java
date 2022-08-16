package atomics;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class AtomicNum {

    public static void main(String[] args) {
        getImage();
    }

    public static void getImage() {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(5);
        ThreadPoolExecutor threadPoolExecutor =
                new ThreadPoolExecutor(5, 5, 100L, TimeUnit.MILLISECONDS, blockingQueue);
        for (int i = 0; i < 100; i++) {
            System.out.println("resize will be done for " + i);
            threadPoolExecutor.submit(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("resize done");
            });

        }
        threadPoolExecutor.shutdown();
    }
}
