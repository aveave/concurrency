package secondsection;

public class BasicThreadSecond extends Thread {

    private BasicThread basicThread;

    public void run() {
        System.out.println("Hello from " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        BasicThreadSecond basicThread = new BasicThreadSecond();
        basicThread.start();
    }
}
