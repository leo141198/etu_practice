package Algorithm.Sort;

import Algorithm.Structure.SortStep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class Sort {
    public ArrayList<Integer> array;
    protected LinkedList<SortStep> steps = new LinkedList<SortStep>();
    protected ListIterator<SortStep> current = null;

    public Sort(ArrayList<Integer> array) {
        this.array = new ArrayList<Integer>(array);
    }

    protected void swap(int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    public SortStep nextStep() {
        try {
            return new SortStep(current.next());
        } catch (NoSuchElementException exp) {
            return null;
        }

    }

    public SortStep prevStep() {
        try {
            return new SortStep(current.previous());
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    public abstract void sort();
}
