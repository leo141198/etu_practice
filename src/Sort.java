import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

public abstract class Sort {
    private ArrayList<Integer> array;
    private LinkedList<Pair> steps = new LinkedList<Pair>();
    private ListIterator<Pair> current = steps.listIterator();

    public Sort(ArrayList<Integer> array) {
        this.array = new ArrayList<Integer>(array);
    }

    protected void swap(int i, int j) {
        int temp = array.get(i);
        array.set(i, array.get(j));
        array.set(j, temp);
    }

    abstract void sort();

    abstract Pair nextStep();

    abstract Pair prevStep();
}
