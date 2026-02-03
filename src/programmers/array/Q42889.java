package programmers.array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Q42889 {

    public static void main(String[] args) {
        int N = 5;
        int[] stages = {2, 1, 2, 6, 2, 4, 3, 3};

        System.out.println(Arrays.toString(solution(N, stages)));

        int N2 = 4;
        int[] stages2 = {4,4,4,4,4};

        System.out.println(Arrays.toString(solution(N2, stages2)));
    }

    public static int[] solution(int N, int[] stages) {
        int[] count = new int[N + 2];
        for (int stage : stages) {
            count[stage]++;
        }

        // 왜 HashMap을 썼을까?
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for (int i = 1; i < count.length - 1; i++) {
            if (count[i] == 0) {
                fails.put(i, 0.0);
            } else {
                Double result = count[i] / total;
                total -= count[i];
                fails.put(i, result);
            }
        }

        // O(NlogN)
        return fails.entrySet().stream().sorted((o1, o2) ->
                o1.getValue().equals(o2.getValue()) ? Integer.compare(o1.getKey(), o2.getKey()) : Double.compare(o2.getValue(), o1.getValue()))
                .mapToInt(Map.Entry::getKey).toArray();

        // HashMap과 HashSet은 key 값이 정수형일 경우 65535까지 오름차순을 보장하도록 API가 구현됨
        // Tim Sort 알고리즘은 정렬 기준이 동일한 값에 대해서는 직전의 순서가 유지되는 Stable 정렬 알고리즘임
        // 문제에서 스테이지 개수 N은 1~500 사이의 자연수임
        // -> key를 비교해서 정렬하는 로직은 없어도 정답!
        // 하지만 정렬 로직 있는 걸 추천
//        return fails.entrySet().stream().sorted((o1, o2) ->
//                Double.compare(o2.getValue(), o1.getValue()))
//                .mapToInt(Map.Entry::getKey).toArray();
    }
}
