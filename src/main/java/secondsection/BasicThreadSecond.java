package secondsection;

public class BasicThreadSecond extends Thread {

    private final String path;

    public BasicThreadSecond (String path) {
        this.path = path;
    }

    private BasicThread basicThread;

    public void run() {
        // logic
        System.out.println("Hello from " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        BasicThreadSecond basicThread = new BasicThreadSecond("C:/1");
        basicThread.start();
    }
}
