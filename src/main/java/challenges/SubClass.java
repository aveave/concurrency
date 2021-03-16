package challenges;

public class SubClass extends SuperClass {

    @Override
    public int sumNumber(int p) {
        return p + 100;
    }

    public double sumNumber(double p) {
        return  p + 100;
    }

    public double sumNumber(double p, double t) {
        return  p;
    }


    public static void main(String[] args) {
        SubClass subClass = new SubClass();

        double d = 100;

        System.out.println(subClass.sumNumber(d));

        System.out.println(subClass.sumNumber(100));
    }
}
