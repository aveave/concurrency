package secondsection;

/**
 * @author EAverkin
 */
public class JoinThread {


    public static void main(String[] args) {

//        Thread thread = new Thread(new WhileRunnable());
//        thread.start();
//        try {
//            thread.join();
//        } catch (InterruptedException ex) {
//
//        }
//        System.out.println("Main threead");

        Thread thread1 = new Thread(new TestRunnable(), "thread 1");
        Thread thread2 = new Thread(new TestRunnable(), "thread 2");
        Thread thread3 = new Thread(new TestRunnable(), "thread 3");

        thread1.start();

        try {
//            thread1.join();
            thread1.join(2000);
        } catch (InterruptedException ex) {

        }

        thread2.start();

        try {
            thread1.join();
        } catch (InterruptedException ex) {

        }

        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException ex) {
        }
        System.out.println("All threads are done");
    }

}

class TestRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread is started work " + Thread.currentThread().getName());
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ex) {

        }
        System.out.println("Поток отработал: " + Thread.currentThread().getName());
    }
}


class WhileRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
            System.out.println(123);
    }
}

