package programmers.queue;

import java.util.ArrayDeque;

public class Q07_2 {

    public static void main(String[] args) {
        System.out.println(solution(5, 2));
        System.out.println(solution(5, 3));
    }

    private static int solution(int N, int K) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            deque.add(i);
        }
        System.out.println(deque);

        while (deque.size() > 1) {
            for (int i = 0; i < K - 1; i++) {
                Integer poll = deque.poll();
                deque.add(poll);
            }
            deque.poll();
        }

        return deque.poll();
    }
}
