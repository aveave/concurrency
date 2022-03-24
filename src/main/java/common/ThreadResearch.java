package common;

/**
 * @author EAverkin
 */
public class ThreadResearch {

    public static void main(String[] args) throws InterruptedException {

//        CountThread countThread = new CountThread();
//        countThread.start();
//
//        Thread.sleep(3000);
//        countThread.interrupt();

        PrinterThread printer = new PrinterThread();
        Thread thread = new Thread(printer);
        thread.start();

        String str = "str";
        str.indexOf()

        Thread.sleep(3000);

        thread.interrupt();
    }
}
