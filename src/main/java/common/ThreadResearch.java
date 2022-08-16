package common;

/**
 * @author EAverkin
 */
public class ThreadResearch {

    public static void main(String[] args) throws InterruptedException {

        PrinterThread printer = new PrinterThread();
        Thread thread = new Thread(printer);
        thread.start();

        Thread.sleep(3000);

        thread.interrupt();
    }
}
