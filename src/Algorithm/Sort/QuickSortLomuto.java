package Algorithm.Sort;

import Algorithm.Structure.SortStep;

import java.util.ArrayList;

public class QuickSortLomuto extends QuickSort {
    public QuickSortLomuto(ArrayList<Integer> array) {
        super(array);
    }

    @Override
    protected void sortArray(int from, int to) {
        if (from == to) {
            steps.add(new SortStep(from, null, null, false, false, false, true));
        }
        if (from < to) {
            int pivot = array.get(to);
            int border = from - 1;
            for (int index = from; index < to; index++) {
                if (array.get(index) <= pivot) {
                    border++;
                    swap(border, index);
                    if (border != index) {
                        steps.add(new SortStep(border, index, to, true, true, false, false));
                        steps.add(new SortStep(border, index, to, true, true, true, false));
                    } else {
                        steps.add(new SortStep(border, index, to, false, false, false, false));
                    }
                } else {
                    steps.add(new SortStep(border + 1, index, to, true, false, false, false));
                }
            }
            swap(++border, to);
            steps.add(new SortStep(border, to, to, true, true, true, false));
            steps.add(new SortStep(border, null, null, false, false, false, true));
            sortArray(from, border - 1);
            sortArray(border + 1, to);
        }
    }

}
