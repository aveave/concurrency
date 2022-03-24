package common;

/**
 * @author EAverkin
 */
public class PrinterThread implements Runnable {

    Thread worker;

    public void start() {
        worker = new Thread();
        worker.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println("Print smth");
            } catch (InterruptedException e) {
                System.out.println("interrupted");
                worker.interrupt();
            }
        }
    }
}
