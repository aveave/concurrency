package secondsection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public final class BasicThread {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            System.out.println("Thread is executed " + Thread.currentThread().getName());
            System.out.println("Priority is " + Thread.currentThread().getPriority());
            throw new RuntimeException("RunTimeException is thrown");
        });
        thread.setName("Just Thread");
        thread.setPriority(Thread.MAX_PRIORITY);

        thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                                               @Override
                                               public void uncaughtException(Thread t, Throwable e) {
                                                   System.out.println("A critical error happened in thread " + t.getName() + " the error is " + e.getMessage());
                                               }
                                           });

                System.out.println("Current Thread is " + Thread.currentThread().getName());
        thread.start();
        System.out.println("Current thread is " + Thread.currentThread().getName());
    }
}
