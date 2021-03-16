package thirdsection;

import java.math.BigInteger;

public class ComplexCalculation {

    public static void main(String[] args) throws InterruptedException {
        BigInteger base1 = BigInteger.valueOf(3L);
        BigInteger power1 = BigInteger.valueOf(3L);
        BigInteger base2 = BigInteger.valueOf(3L);
        BigInteger power2 = BigInteger.valueOf(3L);
        ComplexCalculation complexCalculation = new ComplexCalculation();
        System.out.println(complexCalculation.calculateResult(base1, power1, base2, power2));
    }

    public BigInteger calculateResult(BigInteger base1, BigInteger power1, BigInteger base2, BigInteger power2)
            throws InterruptedException {
        BigInteger result = BigInteger.ZERO;
        PowerCalculatingThread calculateThreadFirst = new PowerCalculatingThread(base1, power1);
        PowerCalculatingThread calculateThreadSecond = new PowerCalculatingThread(base2, power2);
        calculateThreadFirst.start();
        calculateThreadSecond.start();
        calculateThreadFirst.join();
        calculateThreadSecond.join();
        result = result.add(calculateThreadFirst.getResult());
        result = result.add(calculateThreadSecond.getResult());
        return result;
    }

    private static class PowerCalculatingThread extends Thread {
        private BigInteger result = BigInteger.ONE;
        private BigInteger base;
        private BigInteger power;

        public PowerCalculatingThread(BigInteger base, BigInteger power) {
            this.base = base;
            this.power = power;
        }

        public void run() {
            result = BigInteger.ONE;

            for (int i = 0; i < power.intValue(); i++) {
                result = result.multiply(base);
            }
        }

        public BigInteger getResult() {
            return result;
        }


    }
}
