package programmers.hash;

import java.util.HashMap;

public class Q42576 {

    public static void main(String[] args) {
        System.out.println(solution(
                new String[]{"A", "B", "C"},
                new String[]{"C", "B"}
        ));
    }

    private static String solution(String[] participant, String[] completion) {

        // 동명이인 가능
        HashMap<String, Integer> hashMap = new HashMap<>();
        for (String c : completion) {
            hashMap.put(c, hashMap.getOrDefault(c, 0) + 1);
        }

        for (String p : participant) {
            if (hashMap.getOrDefault(p, 0) == 0) {
                return p;
            } else {
                hashMap.put(p, hashMap.get(p) - 1);
            }
        }

        return null;
    }
}
