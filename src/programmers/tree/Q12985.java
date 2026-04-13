package programmers.tree;

public class Q12985 {

    public static void main(String[] args) {
        System.out.println(solution(8, 4, 7));
    }

    private static int solution(int N, int A, int B) {
        // N: 참가자 수
        // A: 참가자 번호
        // B: 경쟁자 번호
        // 몇번째 라운드에서 만나는가

        long roundA = A;
        long roundB = B;

        for (int i = 0; roundA != roundB; i++) {
            roundA =  Math.round(roundA / 2.0);
            roundB =  Math.round(roundB / 2.0);
            if (roundA == roundB) {
                return i + 1;
            }
        }

        return 0;
    }
}
