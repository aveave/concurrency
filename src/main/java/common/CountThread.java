package common;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author EAverkin
 */
public class CountThread implements Runnable {

    private Thread worker;

    private final AtomicBoolean FLAG = new AtomicBoolean(true);

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    @Override
    public void run() {

        while(FLAG.get()) {

            try {
                Thread.sleep(1000L);
                System.out.println("inside thread" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void interrupt() {
        System.out.println("interrupt thread");
        FLAG.compareAndSet(true, false);
    }
}
