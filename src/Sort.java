import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public abstract class Sort {
    public ArrayList<Integer> array;
    private LinkedList<Pair> steps = new LinkedList<Pair>();
    private ListIterator<Pair> current = steps.listIterator();

    public Sort(ArrayList<Integer> array) {
        this.array = new ArrayList<Integer>(array);
    }

    protected void swap(int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
        steps.push(Pair.of(i, j));
    }

    Pair nextStep() {
        try {
            return current.next();
        } catch (NoSuchElementException exp) {
            return null;
        }

    }

    Pair prevStep() {
        try {
            return current.previous();
        } catch (NoSuchElementException exp) {
            return null;
        }
    }

    abstract void sort();
}
