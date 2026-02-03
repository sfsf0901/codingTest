package programmers.array;

import java.util.ArrayList;
import java.util.Arrays;

public class Q42840 {

    public static void main(String[] args) {

        int[] answers1 = {1,2,3,4,5};
        int[] answers2 = {1,3,2,4,2};

        System.out.println(Arrays.toString(solution(answers1)));
        System.out.println(Arrays.toString(solution(answers2)));
    }

    public static int[] solution(int[] answers) {
        int[][] persons = {
                {1, 2, 3, 4, 5},
                {2, 1, 2, 3, 2, 4, 2, 5},
                {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}
        };

        int[] score = new int[3];

        for (int i = 0; i < answers.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (answers[i] == persons[j][i % persons[j].length]) {
                    score[j]++;
                }
            }
        }

        int maxScore = Arrays.stream(score).max().getAsInt();
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (score[i] == maxScore) {
                answer.add(i + 1);
            }
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}
