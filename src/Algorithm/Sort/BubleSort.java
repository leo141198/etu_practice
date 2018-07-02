package Algorithm.Sort;

import Algorithm.Structure.SortStep;

import java.util.ArrayList;

public class BubleSort extends Sort {
    public BubleSort(ArrayList<Integer> array) {
        super(array);
    }

    public void sort() {
        int n = array.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {
                    steps.add(new SortStep().setFirst(j).setFirstChanged().setSecond(j + 1).setSecondChanged());
                    swap(j, j + 1);
                    steps.add(new SortStep().setFirst(j).setFirstChanged().setSecond(j + 1).setSecondChanged().setSwap());
                } else {
                    steps.add(new SortStep().setFirst(j).setSecond(j + 1));
                }
            }
            steps.add(new SortStep().setFirst(n - i - 1).setFinalPos());
        }
        steps.add(new SortStep().setFirst(0).setFinalPos());
        current = steps.listIterator();
    }
}
