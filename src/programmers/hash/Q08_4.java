package programmers.hash;

import java.util.Arrays;
import java.util.HashSet;

public class Q08_4 {

    public static void main(String[] args) {
        System.out.println(solution(
                new int[]{1, 2, 3, 4, 8},
                6
        ));
        System.out.println(solution(
                new int[]{2, 3, 5, 9},
                10
        ));
    }

    private static boolean solution(int[] arr, int target) {

        HashSet<Integer> hashSet = new HashSet<>();

        for (Integer i : arr) {
            if (hashSet.contains(target - i)) {
                return true;
            } else {
                hashSet.add(i);
            }
        }

        return false;
    }
}
