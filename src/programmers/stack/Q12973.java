package programmers.stack;

import java.util.ArrayDeque;

public class Q12973 {

    public static void main(String[] args) {
        System.out.println(solution("baabaa"));
        System.out.println(solution("cdcd"));
    }

    static int solution(String s) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && c == stack.peek()) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        if (stack.isEmpty()) {
            return 1;
        } else {
            return 0;
        }

        // stack이 비어 있거나, 제일 최근 입력된 값과 같지 않다면 값 저장
        // 그냥 pop 안되는 상황이면 push 하는게 나으려나?

        // stack 비어 있으면 1, 아니면 0
    }
}
