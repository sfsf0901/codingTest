package programmers.queue;

import java.util.ArrayDeque;
import java.util.Arrays;

public class Q159994 {

    public static void main(String[] args) {

        String answer1 = solution(
                new String[]{"i", "drink", "water"},
                new String[]{"want", "to"},
                new String[]{"i", "want", "to", "drink", "water"}
        );
        String answer2 = solution(
                new String[]{"i", "water", "drink"},
                new String[]{"want", "to"},
                new String[]{"i", "want", "to", "drink", "water"}
        );

        System.out.println(answer1);
        System.out.println(answer2);
    }

    private static String solution(String[] cards1, String[] cards2, String[] goal) {

        int cardIdx1 = 0;
        int cardIdx2 = 0;

        for (int i = 0; i < goal.length; i++) {
            String target = goal[i];

            if (cardIdx1 < cards1.length && target.equals(cards1[cardIdx1])) {
                cardIdx1++;
            } else if (cardIdx2 < cards2.length && target.equals(cards2[cardIdx2])) {
                cardIdx2++;
            }
            else{
                return "No";
            }
        }

        return "Yes";
    }

    private static String solution2(String[] cards1, String[] cards2, String[] goal) {

        ArrayDeque<String> queue1 = new ArrayDeque<>(Arrays.asList(cards1));
        ArrayDeque<String> queue2 = new ArrayDeque<>(Arrays.asList(cards2));

        boolean result = true;

        for (String word : goal) {
            if (!queue1.isEmpty() && queue1.peek().equals(word)) {
                queue1.poll();
            } else if (!queue2.isEmpty() && queue2.peek().equals(word)) {
                queue2.poll();
            } else  {
                result = false;
            }
        }

        return result ? "Yes" : "No";
    }

}
