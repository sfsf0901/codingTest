package programmers.tree;

import java.util.Arrays;
import java.util.HashMap;

public class Q77486 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(
                new String[]{"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"},
                new String[]{"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"},
                new String[]{"young", "john", "tod", "emily", "mary"},
                new int[]{12, 4, 2, 5, 10}
        )));

    }

    private static HashMap<String, String> pair = new HashMap<>();
    private static HashMap<String, Integer> result = new HashMap<>();

    // 시간 복잡도: O(M * log(amount))
    private static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {

        // O(N)
        for (int i = 0; i < enroll.length; i++) {
            pair.put(enroll[i], referral[i]);
        }

        // O(M)
        for (int i = 0; i < seller.length; i++) {
            calculate(seller[i], amount[i] * 100);
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = result.getOrDefault(enroll[i], 0);
        }

        return answer;
    }

    private static void calculate(String seller, int income) {
        if (seller.equals("-")) {
            return;
        }

        int share = income / 10;
        result.put(seller, result.getOrDefault(seller, 0) + income - share);

        if (share == 0) {
            return;
        }

        calculate(pair.get(seller), share);
    }

    // 프로그래머스 풀이
    // 시간 복잡도: O(M * log(amount))
    private static int[] solution2(String[] enroll, String[] referral, String[] seller, int[] amount) {
        HashMap<String, String> parent = new HashMap<>();
        for (int i = 0; i <enroll.length; i++) {
            parent.put(enroll[i], referral[i]);
        }

        HashMap<String, Integer> total = new HashMap<>();

        for (int i = 0; i < seller.length; i++) {
            String curName = seller[i];
            int money = amount[i] * 100;

            while (money > 0 && !curName.equals("-")) {
                total.put(curName, total.getOrDefault(curName, 0) + money - (money / 10));

                curName = parent.get(curName);
                money /= 10;
            }
        }

        int[] answer = new int[enroll.length];
        for (int i = 0; i < enroll.length; i++) {
            answer[i] = total.getOrDefault(enroll[i], 0);
        }

        return answer;
    }
}
