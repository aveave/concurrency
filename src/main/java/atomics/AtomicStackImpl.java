package atomics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author EAverkin
 */
public class AtomicStackImpl {

    public static void main(String[] args) throws InterruptedException {
        LockFreeStack<Integer> standardStack = new LockFreeStack<>();
        Random random = new Random();

        for (int i = 0; i < 100000; i ++) {
            standardStack.push(random.nextInt());
        }

        List<Thread> threads = new ArrayList<>();
        int pushingThreads = 2;
        int poppingThreads = 2;

        for (int i =0; i < pushingThreads; i++) {
            Thread thread = new Thread(() -> {
                while(true) {
                    standardStack.push(random.nextInt());
                }
            });
            thread.setDaemon(true);
            threads.add(thread);
        }

        for (int i = 0; i < poppingThreads; i++) {
            Thread thread = new Thread(() -> {
               while (true) {
                   standardStack.pop();
               }
            });
            thread.setDaemon(true);
            threads.add(thread);
        }

        threads.forEach(Thread::start);

        Thread.sleep(1000);

        System.out.println(standardStack.getCounter() + " operations were performed in 10 seconds");
    }

    public static class LockFreeStack<T> {
        private AtomicReference<StackNode<T>> head = new AtomicReference<>();
        private AtomicInteger counter = new AtomicInteger();

        public void push(T value) {
            StackNode<T> newHead = new StackNode<>(value);

            while (true) {
                StackNode<T> currentHeadNode = head.get();
                newHead.next = currentHeadNode;
                if (head.compareAndSet(currentHeadNode, newHead)) {
                    break;
                } else {
                    LockSupport.parkNanos(1);
                }
            }
            newHead.next = head.get();
            head.compareAndSet(head.get(), newHead);
            counter.incrementAndGet();
        }

        public T pop() {
            StackNode<T> currentHeadNode = head.get();
            StackNode<T> newHeadNode;

            while (currentHeadNode != null) {
                newHeadNode = currentHeadNode.next;
                if (head.compareAndSet(currentHeadNode, newHeadNode)) {
                    break;
                } else {
                    LockSupport.parkNanos(1);
                    currentHeadNode = head.get();
                }
            }
            counter.incrementAndGet();
            return currentHeadNode != null ? currentHeadNode.value : null;
        }

        public int getCounter() {
            return counter.get();
        }
    }

    private static class StackNode<T> {
        public T value;
        public StackNode<T> next;

        public StackNode(T value) {
            this.value = value;
        }
    }
}
