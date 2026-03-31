package programmers.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

// map, stream을 잘 알아야 코드가 간결해지는 문제
public class Q42579 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
                new String[]{"classic", "pop", "classic", "classic", "pop"},
                new int[]{500, 600, 150, 800, 2500}
        )));
    }

    // 시간 복잡도: O(N)
    private static int[] solution(String[] genres, int[] plays) {

        // 우선 고유번호를 장르별로 분류해야 한다 -> 그래서 미리 분류하는 것은 힘들듯
        // 장르가 몇개인지 모른다

        HashMap<String, Integer> firsts = new HashMap<>();
        HashMap<String, Integer> seconds = new HashMap<>();
        HashMap<String, Integer> total = new HashMap<>();

        // 장르별 총 재생 횟수 및 장르별 노래 순위 결정
        // O(N)
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];

            // 총 재생 횟수
            total.put(genre, total.getOrDefault(genre, 0) + plays[i]);

            // 순위
            if (!firsts.containsKey(genre)) {
                firsts.put(genre, i);
            } else if (plays[i] > plays[firsts.get(genre)]) {
                seconds.put(genre, firsts.get(genre));
                firsts.put(genre, i);
            } else if (plays[i] == plays[firsts.get(genre)]) {
                seconds.put(genre, i);
            } else if(!seconds.containsKey(genre) || plays[i] > plays[seconds.get(genre)]) {
                seconds.put(genre, i);
            }
        }

        // 장르 순위 결정
        // O(M log M) -> 최대 100개이므로 상수로 봄
        Stream<Map.Entry<String, Integer>> sorted = total.entrySet()
                .stream()
                .sorted((g1, g2) -> Integer.compare(g2.getValue(), g1.getValue()));

        ArrayList<Integer> answer = new ArrayList<>();
        // O(M)
        sorted.forEach(e -> {
            answer.add(firsts.get(e.getKey()));
            if (seconds.containsKey(e.getKey())) {
                answer.add(seconds.get(e.getKey()));
            }
        });

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
