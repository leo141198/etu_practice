package Algorithm.Sort;

import Algorithm.Structure.SortStep;

import java.util.ArrayList;

public class QuickSortHoara extends QuickSort {
    public QuickSortHoara(ArrayList<Integer> array) {
        super(array);
    }

    @Override
    protected void sortArray(int from, int to) {
        if (from < to) {
            int pivot_loc = from + (to - from) / 2;
            int pivot = array.get(pivot_loc);
            int i = from, j = to;
            while (i <= j) {
                while (i <= j && array.get(i) < pivot) {
                    steps.add(new SortStep().setFirst(i).setSecond(j).setPivot(pivot_loc));
                    i++;
                }
                while (i <= j && array.get(j) > pivot) {
                    steps.add(new SortStep().setFirst(i).setFirstChanged().setSecond(j).setPivot(pivot_loc));
                    j--;
                }
                if (i <= j) {
                    swap(i, j);
                    steps.add(new SortStep().setFirst(i).setFirstChanged().setSecond(j).setSecondChanged().setPivot(pivot_loc));
                    if (i == pivot_loc) {
                        pivot_loc = j;
                    } else if (j == pivot_loc) {
                        pivot_loc = i;
                    }
                    steps.add(new SortStep().setFirst(i).setFirstChanged().setSecond(j).setSecondChanged().setPivot(pivot_loc).setSwap());
                    i++;
                    j--;
                }
            }
            if (i - j == 2) {
                steps.add(new SortStep().setFirst(i - 1).setFinalPos());
            }
            if (from < j) {
                sortArray(from, j);
            } else if (j >= 0) {
                steps.add(new SortStep().setFirst(j).setFinalPos());
            }
            if (to > i) {
                sortArray(i, to);
            } else {
                steps.add(new SortStep().setFirst(i).setFinalPos());
            }
        }
    }

}
