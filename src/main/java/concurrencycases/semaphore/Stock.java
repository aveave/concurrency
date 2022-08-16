package concurrencycases.semaphore;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

public class Stock {

    private final SemaphoreLock semaphoreLock;

    public Stock(SemaphoreLock semaphoreLock) {
        this.semaphoreLock = semaphoreLock;
    }

    ArrayBlockingQueue<String> products = new ArrayBlockingQueue<>(20);

    public void putProducts() throws InterruptedException {
        System.out.println("Try to put product");
        semaphoreLock.acquire();
        System.out.println("Put product");
        products.put("new product" + new Random().nextInt());
    }

    public void getProducts() throws InterruptedException {
        System.out.println("Try to get products");
        semaphoreLock.release();
        System.out.println("Get product");
        System.out.println(products.take());
    }

}
