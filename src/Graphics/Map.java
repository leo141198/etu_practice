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
    private Timer timer;
    private Integer delay;

    public Map() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            list.add(random.nextInt(1000));
        }
        init(list, true);
        timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nextStep();
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paintComponent(g);
        setBackground(new Color(255, 255, 255));
        float elem_width = (float) getWidth() / elems.size();
        float elem_height;
        for (int i = 0; i < elems.size(); i++) {
            elem_height = (elems.get(i).getValue() / (float) max_value) * getHeight();
            elems.get(i).setRectangle(i * elem_width, (float) getHeight(), elem_width, elem_height);
            elems.get(i).paint(g);
        }
    }

    public void init(ArrayList<Integer> array, boolean rgb) {
        this.max_value = Collections.max(array);
        for (Integer num : array) {
            this.elems.add(new SortElem(num, 0, 0, 0, 0));
        }
        this.sort = new QuickSort(array);
        sort.sort();
        if (rgb) {
            setRGBcolors();
        }
        repaint();
    }

    private void nextStep() {
        Pair pair;
        if ((pair = sort.nextStep()) != null) {
            swapElems(pair.getFirst(), pair.getSecond());
        } else {
            timer.stop();
        }
    }

    private void swapElems(int i, int j) {
        Collections.swap(elems, i, j);
        repaint();
    }

    private void setRGBcolors() {
        for (int i = 0; i < sort.array.size(); i++) {
            for (SortElem elem : elems) {
                if (sort.array.get(i).equals(elem.getValue())) {
                    if (i < sort.array.size() / 6) {
                        elem.setColor(new Color(255, (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6))), 0));
                    } else if (i < sort.array.size() / 6 * 2) {
                        elem.setColor(new Color(255 - (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6))), 255, 0));
                    } else if (i < sort.array.size() / 6 * 3) {
                        elem.setColor(new Color(0, 255, (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6)))));
                    } else if (i < sort.array.size() / 6 * 4) {
                        elem.setColor(new Color(0, 255 - (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6))), 255));
                    } else if (i < sort.array.size() / 6 * 5) {
                        elem.setColor(new Color((255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6))), 0, 255));
                    } else if(i < sort.array.size() - 2) {
                        elem.setColor(new Color(255, 0, 255 - (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6)))));
                    } else {
                        elem.setColor(new Color(255,0,0));
                    }
                }
            }
        }
    }
}
