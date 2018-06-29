import java.util.ArrayList;

public class QuickSort extends Sort {

    public QuickSort(ArrayList<Integer> array) {
        super(array);
    }

    @Override
    public void sort() {
        sortArray(0, array.size() - 1);
    }

    private void sortArray(int from, int to) {
        if (from < to) {
            int pivot = array.get(to);
            int border = from - 1;
            for (int index = from; index < to; index++) {
                if (array.get(index) <= pivot) {
                    border++;
                    swap(border, index);
                }
            }
            swap(++border, to);
            sortArray(from, border - 1);
            sortArray(border + 1, to);
        }
    }
}
