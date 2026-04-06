package programmers.hash;

import java.util.*;

public class Q72411_R {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
                new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"},
                new int[]{2,3,4}
        )));
    }

    private static HashMap<Integer, HashMap<String, Integer>> map = new HashMap<>();

    private static String[] solution(String[] orders, int[] course) {
        // 가장 많이 함께 주문한 단품 메뉴
        // 최소 2명 이상의 손님으로부터 주문된 단품 메뉴
        // 2가지 이상 단품 메뉴로 구성 예정
        // 오름차순 정렬

        // 조합
        // 자바에는 조합관련 메서드가 없기 때문에 재귀로 직접 구현해야 함

        for (int i : course) {
            map.put(i, new HashMap<>());
        }

        for (String order : orders) {
            char[] orderArr = order.toCharArray();
            Arrays.sort(orderArr);
            combinations(0, orderArr, "");
        }

        ArrayList<String> answer = new ArrayList<>();

        for (HashMap<String, Integer> courseMap : map.values()) {
            courseMap.values()
                    .stream()
                    .max(Comparator.comparingInt(o -> o))
                    .ifPresent(cnt -> courseMap.entrySet()
                            .stream()
                            .filter(entry -> cnt.equals(entry.getValue()) && cnt > 1)
                            .forEach(entry -> answer.add(entry.getKey())));
        }

        Collections.sort(answer);
        return answer.toArray(new String[0]);
    }

    private static void combinations(int index, char[] orderArr, String result) {
        if (map.containsKey(result.length())) {
            HashMap<String, Integer> courseMap = map.get(result.length());
            courseMap.put(result, courseMap.getOrDefault(result, 0) + 1);
        }

        for (int i = index; i < orderArr.length; i++) {
            combinations(i + 1, orderArr, result + orderArr[i]);
        }
    }
}
