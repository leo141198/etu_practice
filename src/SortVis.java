import java.util.ArrayList;
import java.util.Scanner;

public class SortVis {

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Scanner keyboard = new Scanner(System.in);
        while (keyboard.hasNextInt()) {
            list.add(keyboard.nextInt());
        }
        Sort sort = new QuickSort(list);
        Pair step;
        sort.sort();
        while ((step = sort.nextStep()) != null) {
            System.out.println(step.getFirst() + " " + step.getSecond());
        }
        for(Integer i : sort.array) {
            System.out.print(i + " ");
        }
    }

}
