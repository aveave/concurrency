package collections;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SynchronizedListResearch {

    public static void main(String[] args) {


        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);

        Collections.synchronizedList(list);
    }
}
