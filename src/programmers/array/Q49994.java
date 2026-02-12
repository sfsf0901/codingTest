package programmers.array;

import java.util.HashMap;
import java.util.HashSet;

public class Q49994 {

    public static void main(String[] args) {
        String dirs1 = "ULURRDLLU";
        String dirs2 = "LULLLLLLU";

        System.out.println(solution(dirs1));
        System.out.println(solution(dirs2));
    }


    private static int solution(String dirs) {
        HashSet<String> countSet = new HashSet<>();

        int currentX = 5;
        int currentY = 5;
        int tempX = 5;
        int tempY = 5;

        for (String dir : dirs.split("")) {
            switch (dir) {
                case "D":
                    if (tempY + 1 <= 10) {
                        tempY++;
                        String path = makePath(currentX, currentY, currentX, tempY);
                        String reversePath = makePath(currentX, tempY, currentX, currentY);
                        countSet.add(path);
                        countSet.add(reversePath);

                        currentY = tempY;
                    }
                    break; // 계속 실행
                case "U":
                    if (tempY - 1 >= 0) {
                        tempY--;
                        String path = makePath(currentX, currentY, currentX, tempY);
                        String reversePath = makePath(currentX, tempY, currentX, currentY);
                        countSet.add(path);
                        countSet.add(reversePath);

                        currentY = tempY;
                    }
                    break;
                case "R":
                    if (tempX + 1 <= 10) {
                        tempX++;
                        String path = makePath(currentX, currentY, tempX, currentY);
                        String reversePath = makePath(tempX, currentY, currentX, currentY);
                        countSet.add(path);
                        countSet.add(reversePath);

                        currentX = tempX;
                    }
                    break;
                case "L":
                    if (tempX - 1 >= 0) {
                        tempX--;
                        String path = makePath(currentX, currentY, tempX, currentY);
                        String reversePath = makePath(tempX, currentY, currentX, currentY);
                        countSet.add(path);
                        countSet.add(reversePath);

                        currentX = tempX;
                    }
                    break;
            }

        }

        return countSet.size() / 2;
    }

    private static String makePath(int x1, int y1, int x2, int y2) {
        return "" + x1 + y1 + x2 + y2;
    }


    // 코딩 테스트 합격자 되기 풀이
    private static final HashMap<Character, int[]> location = new HashMap<>();

    private static void initLocation() {
        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});
    }

    private static int solution2(String dirs) {
        initLocation();
        int x = 5;
        int y = 5;
        HashSet<String> answer = new HashSet<>();

        for (String dir : dirs.split("")) {
            int[] offset = location.get(dir);
            int nx = x + offset[0];
            int ny = y + offset[1];

            if (!isValidMove(nx, ny)) {
                continue;
            }
            answer.add(x + " " + y + " " + nx + " " + ny);
            answer.add(nx + " " + ny + " " + x + " " + y);

            x = nx;
            y = ny;
        }

        return answer.size() / 2;
    }

    private static boolean isValidMove(int nx, int ny) {
        return 0 <= nx && nx < 11 && 0 <= ny && ny < 11;
    }


    // 실패: 현재 코드는 "방문한 좌표"를 세고 있는데, 이 문제(프로그래머스 49994 - 방문 길이)는 "처음 걸어본 길"을 세는 문제
    private static int solution3(String[] dirs) {
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
