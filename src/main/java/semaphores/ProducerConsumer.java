package semaphores;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ProducerConsumer {

    private static Semaphore empty = new Semaphore(1);
    private static Semaphore full = new Semaphore(0);
    private static List<Integer> list = new ArrayList<>();

    private static Queue<Integer> queue = new ArrayDeque();
    private static Lock lock = new ReentrantLock();

    private static int temp;

    public static void main(String[] args) {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }

    public static class Producer implements Runnable {

        private int p = 0;

        @Override
        public void run() {
            while (true) {
                try {
                    empty.acquire();
                    Thread.sleep(1000);
                    lock.lock();
                    queue.offer(p++);
//                    temp = p++;
                    lock.unlock();
                    full.release();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    full.acquire();
                    Thread.sleep(1000);
                    lock.lock();
                    System.out.println(queue.poll());
                    lock.unlock();
                    empty.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
