package collections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentMapResearch {

    public static void main(String[] args) {

        Map<String, String> map = new HashMap<>();

        map.put(null, "null value");
        map.put("null", null);
        System.out.println(map);

        ConcurrentMap<String, String> concurrentMap = new ConcurrentHashMap<>();

//        concurrentMap.put(null, "null value");
        concurrentMap.put("null", null);
        System.out.println(map);

    }
}
