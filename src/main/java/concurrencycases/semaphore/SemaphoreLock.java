package concurrencycases.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SemaphoreLock {

    private int permits;

    private final Lock lock = new ReentrantLock();
    private final Condition permitsAvailable = lock.newCondition();

    public SemaphoreLock(int permits) {
        this.permits = permits;
    }

    public void acquire() throws InterruptedException {
        lock.lock();
        try {
            while (permits <= 0) {
                permitsAvailable.await();
            }
            --permits;
        } finally {
            lock.unlock();
        }
    }

    public void release() {
        lock.lock();
        try {
            ++permits;
            permitsAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        SemaphoreLock semaphoreLock = new SemaphoreLock(2);
        Stock stock = new Stock(semaphoreLock);
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        for (int i = 0; i < 6; i++) {
            executorService.submit(() -> {
                try {
                    stock.putProducts();
                    stock.getProducts();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
