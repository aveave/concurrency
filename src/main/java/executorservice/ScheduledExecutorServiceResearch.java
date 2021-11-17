package executorservice;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author EAverkin
 */
public class ScheduledExecutorServiceResearch {

    public static void main(String[] args) {
        scheduleWithFixedDelay();
    }

    public static void schedule() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> System.out.println("Hello"),
                10,
                TimeUnit.SECONDS);
    }

    /**
     * запускает следующую таску после выполнения предыдущей + делей
     */
    public static void scheduleWithFixedDelay() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleWithFixedDelay(() -> {
                    System.out.println("Hello");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }, 10,
                7,
                TimeUnit.SECONDS);

    }

    /**
     * запускает следующую таску сразу, если ее выполнение длилось дольше заданного делея
     */
    public static void scheduleAtFixedRate() {
        ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(2);
        executorService1.scheduleAtFixedRate(
                () -> {
                    System.out.println("Hello again");
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                },
                10,
                7,
                TimeUnit.SECONDS);
    }
}
