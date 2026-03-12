package programmers.stack;

import java.util.ArrayDeque;
import java.util.HashMap;

public class Q76502 {

    public static void main(String[] args) {
        System.out.println(solution("[](){}"));
        System.out.println(solution("}]()[{"));
        System.out.println(solution("[)(]"));
        System.out.println(solution("}}}"));
    }

    // 시간 복잡도: O(n^2)
    static int solution(String s) {
        // 어떻게 회전시킬 것인가 -> s에 s를 더한다
        // 어떻게 탐색할 것인가 -> 완전 탐색
        // 어떻게 검증할 것인가 -> stack이 비어

        // 탐색용 map 미리 세팅해두기 -> 코드를 간결하게 만드는데 도움이 된다
        HashMap<Character, Character> map = new HashMap<>();
        map.put(']', '[');
        map.put('}', '{');
        map.put(')', '(');

        int len = s.length();
        s += s;
        int answer = 0;

        for (int i = 0; i < len; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            boolean isCorrect = true;

            for (int j = i; j < i + len; j++) {
                char c = s.charAt(j);

                if (!map.containsKey(c)) {
                    stack.push(c);
                } else {
                    if (stack.isEmpty() || stack.pop() != map.get(c)) {
                        isCorrect = false;
                        break;
                    }
                }
            }

            if (stack.isEmpty() && isCorrect) {
                answer++;
            }
        }

        return answer;
    }

    static int solution2(String s) {
        // 회전 횟수에 대한 고민
        // s의 길이만큼 회전하면 원래의 상태로 돌아오므로 (s의 길이 - 1) 만큼 회전한다
        // X, 이건 틀렸다. 시작점도 포함해야 하므로 s의 길이만큼 회전한다고 봐야 한다!

        // 어떻게 회전시킬 것인가
        // 이건 정답 커닝함
        // 문자열 s에 s를 붙여서 한칸씩 이동하도록 함(대단하다..다음엔 이런거 직접 고민해 봐야지..)
        // s += s;

        // 문제 풀이 방법
        // 일단 완전 탐색으로 풀어야 할 듯

        // 그렇다면 어떻게 탐색할 것인가
        // Stack
        // 괄호 별로 따로 Stack을 만들어야 할까? -> 응, {()} 이런 케이스 때문에 그래야 함 -> 아님, 이러면 오히려 오류가 발 생할 수 있음
        // + 하나의 Stack으로 관리해야 나중에 들어온 괄호를 먼저 닫는지 아닌지 체크할 수 있음
        // + 나중에 들어온 괄호를 먼저 닫아야만 옳은 표현임

        // + 그렇다면 stack의 top에 있는 괄호가 pop하려는 괄호와 일치하는 지 확인하는 로직도 필요할까? -> 응 필요해

        // 기타 고려 사항은
        // 무조건 여는 괄호부터 시작해야함. 그렇지 않으면 실패
        // 가독성을 위해서 switch문을 사용하자 -> No, switch문을 빠져나가는 코드가 익숙하지 않고, 오히려 복잡해짐. 그냥 if문 쓰자

        int len = s.length();
        s += s;
        int result = 0;

        for (int i = 0; i < len; i++) {
            ArrayDeque<Character> stack = new ArrayDeque<>();
            boolean isCorrect = true;

            for (int j = i; j < i + len; j++) {
                char c = s.charAt(j);

                if (c == '[' || c == '{' || c == '(') {
                    stack.push(c);
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.pop() != '[') {
                        isCorrect = false;
                        break;
                    }
                } else if (c == '}') {
                    if (stack.isEmpty() || stack.pop() != '{') {
                        isCorrect = false;
                        break;
                    }
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.pop() != '(') {
                        isCorrect = false;
                        break;
                    }
                }
            }

            if (stack.isEmpty() && isCorrect) {
                result ++;
            }
        }

        return result;
    }
}
