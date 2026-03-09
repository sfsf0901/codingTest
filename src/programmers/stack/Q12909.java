package programmers.stack;

import java.util.ArrayDeque;
import java.util.Stack;

public class Q12909 {

    public static void main(String[] args) {
        System.out.println(solution("()()"));
        System.out.println(solution(")()("));
    }

    // 시간 복잡도: O(n)
    static boolean solution(String s) {
        ArrayDeque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(' -> stack.push(c); // O(1)
                case ')' -> {
                    if (stack.isEmpty()) {
                        return false;
                    }
                    stack.pop(); // O(1)
                }
            }
        }

        return stack.isEmpty();
    }

    // 시간 복잡도: O(n)
    static boolean solution2(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(' -> stack.push(c);
                case ')' -> {
                    if (stack.isEmpty()) {
                        answer = false;
                    } else {
                        stack.pop();
                    }
                }
            }
        }

        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }

    // 시간 복잡도: O(n)
    static boolean solution3(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
