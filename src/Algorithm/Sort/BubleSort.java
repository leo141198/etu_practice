package Algorithm.Sort;

import Algorithm.Structure.SortStep;

import java.util.ArrayList;

public class BubleSort extends Sort {
    public BubleSort(ArrayList<Integer> array) {
        super(array);
    }

    public void sort() {
        sortArray(0, array.size() - 1);
        current = steps.listIterator();
    }

    private void sortArray(int from, int to) {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    steps.add(new SortStep(j, j + 1, j, true, true, false, false));
                    swap(j, j + 1);
                    steps.add(new SortStep(j, j + 1, j, true, true, true, false));
                } else {
                    steps.add(new SortStep(j, j + 1, j, false, false, false, false));
                }
            }
            steps.add(new SortStep(n - 1 - i, null, null, false, false, false, true));
        }
        steps.add(new SortStep(0, null, null, false, false, false, true));
    }
}
