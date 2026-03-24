package programmers.hash;

import java.util.HashMap;

public class Test {

    public static void main(String[] args) {
        HashMap<String, Integer> hashMap = new HashMap<>();

        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("C", 1);
        hashMap.put("D", 2);

        System.out.println(hashMap.getOrDefault("A", 0));
        System.out.println(hashMap.getOrDefault("E", 0));

    }
}
