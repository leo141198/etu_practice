package Algorithm.Sort;

import java.util.ArrayList;

public abstract class QuickSort extends Sort {

    public QuickSort(ArrayList<Integer> array) {
        super(array);
    }

    @Override
    public void sort() {
        sortArray(0, array.size() - 1);
        current = steps.listIterator();
    }

    abstract void sortArray(int from, int to);
}
