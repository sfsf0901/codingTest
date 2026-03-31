package programmers.hash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Q42888 {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution(new String[]{
                "Enter uid1234 Muzi",
                "Enter uid4567 Prodo",
                "Leave uid1234",
                "Enter uid1234 Prodo",
                "Change uid4567 Ryan"})));
    }

    // 시간 복잡도: O(N)
    private static String[] solution(String[] record) {
        // Enter: [닉네임]님이 들어왔습니다.
        // Leave: [닉네임]님이 나갔습니다.
        // Change: -

//        LinkedHashMap<String, String> commands = new LinkedHashMap<>();
        ArrayList<String> ids = new ArrayList<>();
        ArrayList<String> messages = new ArrayList<>();
        HashMap<String, String> nicknames = new HashMap<>();

        // O(N)
        for (String str : record) {
            String[] parts = str.split(" ");
            String command = parts[0];
            String id = parts[1];

            if (command.equals("Enter")) {
                String nickname = parts[2];
                ids.add(id);
                messages.add(command);
                nicknames.put(id, nickname);
            } else if (command.equals("Leave")) {
                ids.add(id);
                messages.add(command);
            } else {
                String nickname = parts[2];
                nicknames.put(id, nickname);
            }
        }

        int length = ids.size();
        String[] answer = new String[length];

        // O(N) 보다 적음
        for (int i = 0; i < length; i++) {
            String nickname = nicknames.get(ids.get(i));
            String message = messages.get(i);
            answer[i] = nickname + (message.equals("Enter") ? "님이 들어왔습니다." : "님이 나갔습니다.");
        }

        return answer;
    }
}
