package challenges;

public class DataRace {

    public static void main(String[] args) {

        SharedClass sharedClass = new SharedClass();

        Thread incrementThread = new Thread(() -> {
            for (int i =0; i < Integer.MAX_VALUE; i++) {
                sharedClass.increment();
            }
        });

        Thread checkThread = new Thread(() -> {
            for (int i=0; i < Integer.MAX_VALUE; i++) {
                sharedClass.checkVariables();
            }
        });

        incrementThread.start();
        checkThread.start();
    }

    private static class SharedClass {

        private int x;
        private int y;

        public void increment() {
            x++;
            y++;
        }

        public void checkVariables() {
            if (y > x) {
                System.out.println("Error is happend  y > x");
            }
        }
    }
}
