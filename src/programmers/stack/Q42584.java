package programmers.stack;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Q42584 {

    public static void main(String[] args) {
        int[] answer1 = solution(new int[]{1, 2, 3, 2, 3});
        System.out.println(Arrays.toString(answer1));

//        int[] answer2 = solution(new int[]{1, 3, 7, 9, 5, 1, 9});
//        System.out.println(Arrays.toString(answer2));
    }

    // 시간 복잡도: O(2n) -> O(n)
    // solution2 개선
    static int[] solution(int[] prices) {
        int length = prices.length;
        int[] answer = new int[length];

        ArrayDeque<Integer> indexes = new ArrayDeque<>();
        indexes.push(0);

        for (int i = 1; i < length ; i++) {
            while (!indexes.isEmpty() && prices[i] < prices[indexes.peek()]) {
                int index = indexes.pop();
                answer[index] = i - index;
            }

            indexes.push(i);
        }

        while (!indexes.isEmpty()) {
            int targetIndex = indexes.pop();
            answer[targetIndex] = length - 1 - targetIndex;
        }

        return answer;
    }

    // 시간 복잡도: O(2n) -> O(n)
    static int[] solution2(int[] prices) {
        int[] answer = new int[prices.length];

        ArrayDeque<Integer> indexes = new ArrayDeque<>();
        indexes.push(0);

        for (int i = 1; i < prices.length ; i++) {
            int currentPrice = prices[i];
            int stackPrice = prices[indexes.peek()];

            if (currentPrice < stackPrice) {
                int size = indexes.size();
                for (int j = 0; j < size; j++) {
                    if (prices[indexes.peek()] > currentPrice) {
                        int targetIndex = indexes.pop();
                        answer[targetIndex] = i - targetIndex;
                    }
                }
            }

            indexes.push(i);
        }

        int size = indexes.size();
        int index = indexes.peek();

        for (int i = 0; i < size; i++) {
            int targetIndex = indexes.pop();
            answer[targetIndex] = index - targetIndex;
        }

        return answer;
    }

    // 시간 복잡도: O(n^2)
    static int[] solution3(int[] prices) {
        int[] answer = new int[prices.length];

        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                answer[i]++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
        }

        return answer;
    }
}
