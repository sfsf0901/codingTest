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
                new String[]{"banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana", "banana"}
        ));
    }

    // O(N + M)
    private static int solution(String[] want, int[] number, String[] discount) {

        HashMap<String, Integer> wantMap = new HashMap<>();
        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        HashMap<String, Integer> windowMap = new HashMap<>();
        int matchCount = 0;
        int totalKeys = wantMap.size();

        int answer = 0;

        // 슬라이딩 윈도우
        for (int i = 0; i < discount.length; i++) {
            // 오른쪽 추가
            String add = discount[i];
            if (wantMap.containsKey(add)) {
                int before = windowMap.getOrDefault(add, 0);
                int need = wantMap.get(add);

                if (before == need - 1) {
                    matchCount++;
                } else if (before == need) {
                    matchCount--;      // 충족 → 초과
                }

                windowMap.put(add, before + 1);
            }

            // 윈도우 크기 초과 시 왼쪽 제거
            if (i >= 10) {
                String remove = discount[i - 10];

                if (wantMap.containsKey(remove)) {
                    int before = windowMap.get(remove);
                    int need = wantMap.get(remove);

                    if (before == need) {
                        matchCount--;       // 충족 → 부족
                    } else if (before == need + 1) {
                        matchCount++;  // 초과 → 충족
                    }

                    windowMap.put(remove, before - 1);
                }
            }

            // 윈도우 크기가 10이 됐을 때부터 체크
            if (i >= 9 && matchCount == totalKeys) {
                answer++;
            }
        }

        return answer;
    }


    // O(N*M)
    private static int solution2(String[] want, int[] number, String[] discount) {

        HashMap<String, Integer> discountMap = new HashMap<>();
        HashMap<String, Integer> wantMap = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            discountMap.put(discount[i], discountMap.getOrDefault(discount[i], 0) + 1);
        }

        for (int i = 0; i < want.length; i++) {
            wantMap.put(want[i], number[i]);
        }

        int length = discount.length - 9;

        int answer = 0;

        for (int i = 0; i < length; i++) {
            // 모든 키-값 쌍을 순회하면서 비교
            if (discountMap.equals(wantMap)) {
                answer++;
            }

            if (i < length - 1) {
                if (discountMap.get(discount[i]) - 1 == 0) {
                    discountMap.remove(discount[i]);
                } else {
                    discountMap.put(discount[i], discountMap.get(discount[i]) - 1);
                }

                discountMap.put(discount[i + 10], discountMap.getOrDefault(discount[i + 10], 0) + 1);
            }
        }

        return answer;
    }

    // O(N*M)
    private static int solution3(String[] want, int[] number, String[] discount) {

        HashMap<String, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put(discount[i], hashMap.getOrDefault(discount[i], 0) + 1);
        }

        int length = discount.length - 9;

        int count = 0;

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < want.length; j++) {
                if (number[j] > hashMap.getOrDefault(want[j], 0)) {
                    count--;
                    break;
                }
            }

            count++;

            // 슬라이딩 윈도우
            if (i < length - 1) {
                hashMap.put(discount[i], hashMap.get(discount[i]) - 1);
                hashMap.put(discount[i + 10], hashMap.getOrDefault(discount[i + 10], 0) + 1);
            }
        }

        return count;
    }
}
