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
            steps.add(new SortStep().setFirst(from).setFinalPos());
        }
        if (from < to) {
            int pivot = array.get(to);
            int border = from - 1;
            for (int index = from; index < to; index++) {
                if (array.get(index) <= pivot) {
                    border++;
                    swap(border, index);
                    if (border != index) {
                        steps.add(new SortStep().setFirst(border).setFirstChanged().setSecond(index).setSecondChanged().setPivot(to));
                        steps.add(new SortStep().setFirst(border).setFirstChanged().setSecond(index).setSecondChanged().setPivot(to).setSwap());
                    } else {
                        steps.add(new SortStep().setFirst(border).setSecond(index).setPivot(to));
                    }
                } else {
                    steps.add(new SortStep().setFirst(border + 1).setFirstChanged().setSecond(index).setPivot(to));
                }
            }
            swap(++border, to);
            steps.add(new SortStep().setFirst(border).setFirstChanged().setSecond(to).setSecondChanged().setSwap());
            steps.add(new SortStep().setFirst(border).setFinalPos());
            sortArray(from, border - 1);
            sortArray(border + 1, to);
        }
    }

}
