package streams;

import java.util.stream.Stream;

public class ParallelStreamResearch {

    public static void main(String[] args) {

        Stream<Integer> intStream = Stream.of(1, 2, 3, 5, 6, 7, 8, 9, 4, 1, 2, 3, 4, 5, 6);

//        System.out.println(intStream.reduce(0, a + b).intValue());

    }
}
