package queues;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueResearch {

    public static void main(String[] args) throws InterruptedException {

//        researchArrayBlockingQueue();
        researchLinkedBlockingQueue();
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>();

    }

    private static void researchArrayBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        for (int i = 0; i < 11; i++) {
            // put is blocked if queue is full
            queue.put(i);
            System.out.println("Added element " + i );
        }

        // take is blocked if queue is empty
        System.out.println(queue.take());
    }

    private static void researchLinkedBlockingQueue() throws InterruptedException {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue<>(10);

        for (int i =0; i < 11; i++) {
            queue.put(i);
            System.out.println("Added element " + i);
        }

        System.out.println("Linked " + queue.take());
    }
}
