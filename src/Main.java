import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5};
        System.out.println(arr);
        System.out.println(Arrays.toString(arr));

        List list = new ArrayList();
        list.add(1);
        list.add("abc");
        System.out.println(list);

        int sum = (int)list.get(0) + (int)list.get(1);
    }
}