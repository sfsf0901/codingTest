package programmers.array;

import java.util.Arrays;

public class Q49994 {

    public static void main(String[] args) {
        String[] dirs1 = {"U", "L", "U", "R", "R", "D", "L", "L", "U"};
        String[] dirs2 = {"L", "U", "L", "L", "L", "L", "L", "L", "U"};

        System.out.println(solution(dirs1));
        System.out.println(solution(dirs2));
    }



    // 실패: 현재 코드는 "방문한 좌표"를 세고 있는데, 이 문제(프로그래머스 49994 - 방문 길이)는 "처음 걸어본 길"을 세는 문제
    private static int solution(String[] dirs) {
        boolean[][] visited = new boolean[11][11];
        visited[5][5] = true;
        int row = 5;
        int col = 5;
        int count = 0;

        for (String dir : dirs) {
            switch (dir) {
                case "D":
                    if (row + 1 <= 10) {
                        row++;
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            count++;
                        }
                    }
                    break; // 계속 실행
                case "U":
                    if (row - 1 >= 0) {
                        row--;
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            count++;
                        }
                    }
                    break;
                case "R":
                    if (col + 1 <= 10) {
                        col++;
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            count++;
                        }
                    }
                    break;
                case "L":
                    if (col - 1 >= 0) {
                        col--;
                        if (!visited[row][col]) {
                            visited[row][col] = true;
                            count++;
                        }
                    }
                    break;
            }
        }

        return count;
    }

}
