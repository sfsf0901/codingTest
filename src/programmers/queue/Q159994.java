package programmers.queue;

import java.util.ArrayDeque;

public class Q159994 {

    public static void main(String[] args) {

    }

    private static String solution(String[] cards1, String[] cards2, String[] goal) {

        ArrayDeque<String> queue1 = new ArrayDeque<>();
        ArrayDeque<String> queue2 = new ArrayDeque<>();
        for (int i = 0; i < cards1.length; i++) {
            queue1.add(cards1[i]);
            queue2.add(cards2[i]);
        }

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
