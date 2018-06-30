package Algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class Sort {
    public ArrayList<Integer> array;
    protected LinkedList<Pair> steps = new LinkedList<Pair>();
    protected ListIterator<Pair> current = null;

    public Sort(ArrayList<Integer> array) {
        this.array = new ArrayList<Integer>(array);
    }

    protected void swap(int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
        steps.add(Pair.of(j, i));
    }

    public Pair nextStep() {
        try {
            Pair pair = current.next();
            return new Pair(pair);
        } catch (NoSuchElementException exp) {
            return null;
        }

    }

    public Pair prevStep() {
        try {
            return new Pair(current.previous());
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    public abstract void sort();
}
