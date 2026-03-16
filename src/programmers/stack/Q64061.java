package programmers.stack;

import java.util.ArrayDeque;

public class Q64061 {
    public static void main(String[] args) {
        int answer1 = solution(
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {0, 0, 1, 0, 3},
                        {0, 2, 5, 0, 1},
                        {4, 2, 4, 4, 2},
                        {3, 5, 1, 3, 1}},
                new int[]{1, 5, 3, 5, 1, 2, 1, 4}
        );
        System.out.println(answer1);
    }

    // moves가 길수록 solution이 유리, board가 크고 moves가 짧으면 비슷하거나 solution2가 나을 수도 있음
    // O(N^2)
    public static int solution(int[][] board, int[] moves) {
        // 주어지는 board를 stack에 미리 담아둔다.
        // 배열을 사용하면 필요한 stack의 개수를 미리 알 수 없어도 해결 가능하다!!(세상에...)
        int rowLength = board.length;
        int colLength = board[0].length;
        ArrayDeque<Integer>[] stacks = new ArrayDeque[rowLength];

        // 굿!! 이런 생각을 할 수 있는 개발자가 되어야 할듯
        for (int i = 0; i < stacks.length; i++) {
            stacks[i] = new ArrayDeque<>();
        }

        // O(N^2)
        for (int i = 0; i < colLength; i++) {
            for (int j = rowLength - 1; j >= 0; j--) {
                if (board[j][i] > 0) {
                    stacks[i].push(board[j][i]);
                }
            }
        }

        ArrayDeque<Integer> bucket = new ArrayDeque<>();
        int answer = 0;
        //O(M)
        for (int move : moves) {
            if (!stacks[move - 1].isEmpty()) {
                int doll = stacks[move - 1].pop();
                if (!bucket.isEmpty() && doll == bucket.peek()) {
                    bucket.pop();
                    answer += 2;
                } else {
                    bucket.push(doll);
                }
            }
        }

        return answer;
    }

    public static int solution2(int[][] board, int[] moves) {
        // 크레인 작동 시 인형이 집어지지 않는 경우는 없으나 만약 인형이 없는 곳에서 크레인을 작동시키는 경우에는 아무런 일도 일어나지 않는다
        ArrayDeque<Integer> bucket = new ArrayDeque<>();

        int answer = 0;

        // O(M * N)
        for (int move : moves) {
            int col = move - 1;
            for (int[] dolls : board) {
                if (dolls[col] != 0) {
                    int doll = dolls[col];
                    dolls[col] = 0;
                    if (!bucket.isEmpty() && doll == bucket.peek()) {
                        bucket.pop();
                        answer += 2;
                    } else {
                        bucket.push(doll);
                    }
                    break;
                }
            }
        }

        return answer;
    }
}
