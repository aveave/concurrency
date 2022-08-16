package common;

public class CancelThread implements Runnable {

    private static int temp = 0;
    private volatile boolean flag = true;
    @Override
    public void run() {
        while (flag) {
            try {
                Thread.sleep(1000);
                System.out.println("Print num" + (temp++));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                flag = false;
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(new CancelThread());
        thread.start();

        Thread.sleep(5000);

        System.out.println("Main");
        thread.interrupt();
//        thread.join();
    }
}
