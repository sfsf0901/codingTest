import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        countDown(5);
    }

    private static void countDown(int n) {
        if (n == 0) {
            return;
        }

        System.out.println(n);
        countDown(n - 1);
    }
}