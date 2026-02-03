package programmers.array;

import java.util.Arrays;

public class Q12949 {

    public static void main(String[] args) {
//        int[][] arr1 = new int[][]{{1, 4}, {3, 2}, {4, 1}};
//        int[][] arr2 = new int[][]{{3, 3}, {3, 3}};
        int[][] arr1 = new int[][]{{2,3,2}, {4,2,4}, {3,1,4}};
        int[][] arr2 = new int[][]{{5,4,3}, {2,4,1}, {3,1,1}};


        System.out.println(Arrays.deepToString(solution(arr1, arr2)));
    }

    // O(n3)
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int loop = arr1[0] .length;

        int[][] answer = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int sum = 0;
                for (int k = 0; k < loop; k++) {
                    sum += arr1[i][k] *  arr2[k][j];
                }
                answer[i][j] = sum;
            }
        }

        return  answer;
    }

    public static int[][] solution2(int[][] arr1, int[][] arr2) {
        int row = arr1.length;
        int col = arr2[0].length;
        int loop = arr1[0].length;
        System.out.println(loop);

        int[][] answer = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int result = 0;
                for (int k = 0; k < loop; k++) {
                    result += arr1[i][k] * arr2[j][k];
                }

                answer[i][j] = result;
            }
        }

        return answer;
    }
}
