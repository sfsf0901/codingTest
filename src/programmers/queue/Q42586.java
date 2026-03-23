package programmers.queue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;

public class Q42586 {

    public static void main(String[] args) {

        int[] answer1 = solution(
                new int[]{93, 30, 55},
                new int[]{1, 30, 5}
        );
        int[] answer2 = solution(
                new int[]{95, 90, 99, 99, 80, 99},
                new int[]{1, 1, 1, 1, 1, 1}
        );

        System.out.println(Arrays.toString(answer1));
        System.out.println(Arrays.toString(answer2));
    }

    private static int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();

        int[] days = new int[progresses.length];
        // O(N)
        for (int i = 0; i < progresses.length; i++) {
            days[i] = (int) Math.ceil((100.0 -  progresses[i]) / speeds[i]);
        }

        int count = 1;
        int maxDay = days[0];
        // O(N)
        for (int i = 1; i <days.length; i++) {
            if (days[i] <= maxDay) {
                count++;
            } else {
                answer.add(count);
                maxDay = days[i];
                count = 1;
            }
        }
        answer.add(count);

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    // 책에서 큐로 분류되어 있었기 때문에 큐로 풀었음
    // 하지만 굳이 큐를 사용할 필요가 없는 문제
    // 큐를 사용할 경우 오히려 비효율적
    // 단순 배열만으로도 해결 가능
    private static int[] solution2(int[] progresses, int[] speeds) {

        // 하루에 한번 진도율 계산
        // 100프로 차면 배포
        // 단, 배포 순서는 바뀔수 없음(FIFO)
        // 각 배포마다 몇 개의 기능이 배포되는지 반환

        ArrayDeque<Integer> queue = new ArrayDeque<>();

        // O(N)
        for (int i = 0; i < progresses.length; i++) {
            double progress = 100.0 - progresses[i];
            int days = (int) Math.ceil(progress / speeds[i]);
            queue.add(days);
        }

        ArrayList<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int count = 1;
            int first = queue.poll();
            while (!queue.isEmpty() && first > queue.peek()) {
                queue.poll();
                count++;
            }

            result.add(count);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 프로그래머스 풀이
    // 큐를 전혀 쓰지 않음
    private static int[] solution3(int[] progresses, int[] speeds) {
        Queue<Integer> answer = new ArrayDeque<>();

        int n = progresses.length;

        int[] daysLeft = new int[n];
        for (int i = 0; i < n; i++) {
            daysLeft[i] = (int) Math.ceil((100 - progresses[i]) / speeds[i]);
        }

        int count = 0;
        int maxDay = daysLeft[0];

        for (int i = 0; i < n; i++) {
            if (daysLeft[i] <= maxDay) {
                count++;
            } else {
                answer.add(count);
                count = 1;
                maxDay = daysLeft[i];
            }
        }

        answer.add(count);
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
