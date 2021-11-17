package reentrantlock;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLock {


    public static final int HIGHEST_PRICE = 1000;

    public static void main(String[] args) throws InterruptedException {

       ReentrantReadWriteLock productLock = new ReentrantReadWriteLock();
       Lock readProductLock = productLock.readLock();
       Lock writeProductLock = productLock.writeLock();


        InventoryDatabase inventoryDatabase = new InventoryDatabase();

        Random random = new Random();
        for (int i = 0; i < 100000; i++) {
            inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
        }

        Thread writer = new Thread(() -> {
            while (true) {
                inventoryDatabase.addItem(random.nextInt(HIGHEST_PRICE));
                inventoryDatabase.removeItem(random.nextInt(HIGHEST_PRICE));

                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.setDaemon(true);
        writer.start();

        int numberOfReadersThreads = 7;
        List<Thread> readers = new ArrayList<>();

        for (int i = 0; i < numberOfReadersThreads; i++) {
            Thread readThread = new Thread(() -> {
                int upper = random.nextInt(HIGHEST_PRICE);
                int lower = upper > 0 ? random.nextInt(upper) : 0;
                System.out.println(inventoryDatabase.getNumberOfItemsInPriceRange(lower, upper));
            });
            readThread.setDaemon(true);
            readers.add(readThread);
        }

        long startReadingTime = System.currentTimeMillis();

        readers.forEach(Thread::start);

        for (Thread reader : readers) {
            reader.join();
        }

        long endReadingTime = System.currentTimeMillis();
        System.out.println("Takes time" + (endReadingTime - startReadingTime));
    }

    public static class InventoryDatabase {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        Lock readLock = lock.readLock();
        Lock writeLock = lock.writeLock();
        private TreeMap<Integer, Integer> priceToCountMap = new TreeMap<>();

        public int getNumberOfItemsInPriceRange(int lowerBound, int upperBound) {
            readLock.lock();
            try {
                Integer fromKey = priceToCountMap.ceilingKey(lowerBound);
                Integer toKey = priceToCountMap.floorKey(upperBound);

                if (fromKey == null || toKey == null) {
                    return 0;
                }
                NavigableMap<Integer, Integer> rangeOfPrices = priceToCountMap.subMap(fromKey, true, toKey, true);
                int sum = rangeOfPrices.values().stream().mapToInt(Integer::valueOf).sum();
                return sum;
            } finally {
                readLock.unlock();
            }
        }

        public void addItem(int price) {
            writeLock.lock();
            try {
                Integer numberOfPrice = priceToCountMap.get(price);
                if (numberOfPrice == null) {
                    priceToCountMap.put(price, 1);
                } else {
                    priceToCountMap.put(price, numberOfPrice++);
                }
            } finally {
                writeLock.unlock();
            }
        }

        public void removeItem(int price) {
            writeLock.lock();
            try {
                Integer numberOfPrice = priceToCountMap.get(price);
                if (numberOfPrice == 1) {
                    priceToCountMap.remove(price);
                } else {
                    priceToCountMap.put(price, numberOfPrice--);
                }
            } finally {
                writeLock.unlock();
            }
        }
    }
}


