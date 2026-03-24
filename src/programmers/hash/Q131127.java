package programmers.hash;

import java.util.HashMap;

public class Q131127 {

    public static void main(String[] args) {
        System.out.println(solution(
                new String[]{"banana", "apple", "rice", "pork", "pot"},
                new int[]{3, 2, 2, 2, 1},
                new String[]{"chicken", "apple", "apple", "banana", "rice", "apple", "pork", "banana", "pork", "rice", "pot", "banana", "apple", "banana"}
        ));
        System.out.println(solution(
                new String[]{"apple"},
                new int[]{10},
                new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        ));

    }

    private static int solution(String[] want, int[] number, String[] discount) {

        int length = discount.length - 9;

        int count = 0;

        for (int i = 0; i < length; i++) {
            HashMap<String, Integer> hashMap = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                hashMap.put(discount[j], hashMap.getOrDefault(discount[j], 0) + 1);
            }

            for (int j = 0; j < want.length; j++) {
                if (number[j] > hashMap.getOrDefault(want[j], 0)) {
                    count--;
                    break;
                }
            }

            count++;
        }

        return count;
    }
}
