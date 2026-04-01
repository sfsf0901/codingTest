package programmers.hash;

import java.util.*;

public class Q92334 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
                new String[]{"muzi", "frodo", "apeach", "neo"},
                new String[]{"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"},
                2
        )));
    }

    // 시간 복잡도: O(N)
    private static int[] solution(String[] id_list, String[] report, int k) {
        // 신고
        // 정지 -> 메일 발송
        // 유저별로 처리 결과 메일 받은 횟수를 배열에 담아 반환

        HashMap<String, HashSet<String>> reportedUsers = new HashMap<>();
        HashMap<String, Integer> mailCount = new HashMap<>();

        // O(N)
        for (String r : report) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reportedUser = parts[1];
            reportedUsers.putIfAbsent(reportedUser, new HashSet<>());
            reportedUsers.get(reportedUser).add(reporter);
        }

        // O(N)
        // 바깥 루프는 피신고자 수만큼, 안쪽 루프는 각 피신고자의 신고자 수만큼 돈다.
        // 이 안쪽 루프의 총 반복 횟수를 다 합치면 결국 첫 번째 루프에서 add한 횟수를 넘을 수 없다.
        for (Map.Entry<String, HashSet<String>> entry : reportedUsers.entrySet()) {
            if (entry.getValue().size() >= k) {
                for (String reporter : entry.getValue()) {
                    mailCount.put(reporter, mailCount.getOrDefault(reporter, 0) + 1);
                }
            }
        }

        // O(M)
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCount.getOrDefault(id_list[i], 0);
        }

        return answer;
    }
}
