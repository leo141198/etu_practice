package Graphics;

import Algorithm.Structure.Pair;
import Algorithm.Sort.Sort;
import Algorithm.Sort.QuickSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map extends JPanel {
    private ArrayList<SortElem> elems = new ArrayList<SortElem>();
    private Integer max_value;
    private Sort sort;

    public Map() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for(int i = 0; i < 200; i++) {
            list.add(random.nextInt(1000));
        }
        init(list);
        Timer timer = new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(255, 255, 255));
        float elem_width = (float)getWidth() / elems.size();
        float elem_height;
        for (int i = 0; i < elems.size(); i++) {
            elem_height = (elems.get(i).getValue() / (float)max_value) * getHeight();
            elems.get(i).setRectangle(i * elem_width, (float)getHeight(), elem_width, elem_height);
            elems.get(i).paint(g);
        }
    }

    public void init(ArrayList<Integer> array) {
        this.max_value = Collections.max(array);
        for (Integer num : array) {
            this.elems.add(new SortElem(num, 0, 0, 0, 0));
        }
        this.sort = new QuickSort(array);
        sort.sort();
        repaint();
    }

    public void nextStep() {
        Pair pair;
        if((pair = sort.nextStep()) != null) {
            swapElems(pair.getFirst(), pair.getSecond());
        }
    }

    public void swapElems(int i, int j) {
        Collections.swap(elems, i,j);
        repaint();
    }
}
