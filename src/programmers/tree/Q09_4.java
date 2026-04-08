package programmers.tree;

import java.util.Arrays;

public class Q09_4 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution(
                new int[]{1, 2, 3, 4, 5, 6, 7}
        )));
    }

    // 시간 복잡도: O(N)
    private static String[] solution(int[] nodes) {
        // 전위, 중위, 후위 순회 결과 반환
        String[] answer = new String[3];

        answer[0] = preorder(nodes, 0).trim();
        answer[1] = inorder(nodes, 0).trim();
        answer[2] = postorder(nodes, 0).trim();

        return answer;
    }

    private static String preorder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return nodes[index] + " " +
                preorder(nodes, index * 2 + 1) +
                preorder(nodes, index * 2 + 2);
    }

    private static String inorder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return inorder(nodes, index * 2 + 1) +
                nodes[index] + " " +
                inorder(nodes, index * 2 + 2);
    }

    private static String postorder(int[] nodes, int index) {
        if (index >= nodes.length) {
            return "";
        }

        return postorder(nodes, index * 2 + 1) +
                postorder(nodes, index * 2 + 2) +
                nodes[index] + " ";
    }
}
