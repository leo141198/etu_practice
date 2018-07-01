package Graphics;

import Algorithm.Structure.SortStep;
import Algorithm.Sort.Sort;
import Algorithm.Sort.QuickSort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Map extends JPanel implements ActionListener {
    private ArrayList<SortElem> elems = new ArrayList<SortElem>();
    private Integer max_value;
    private Sort sort;
    protected Timer timer;
    protected Boolean auto_direction = true;
    protected Integer delay;
    protected Boolean rgb;
    protected SortStep current_step;

    public Map() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(50));
        }
        init(list, false);
        timer = new Timer(100, this);
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
        this.rgb = rgb;
        if (rgb) {
            setRGBColors();
        } else {
            setDemoColors();
        }
        repaint();
    }

    protected void setRGB(boolean rgb) {
        this.rgb = rgb;
        if (rgb) {
            setRGBColors();
        } else {
            setDemoColors();
        }
        repaint();
    }

    protected void nextStep() {
        makeStep(true);
    }

    protected void prevStep() {
        makeStep(false);
    }

    private void makeStep(boolean next) {
        if (!this.rgb && current_step != null) {
            unselectStep(current_step);
        }
        if (next) {
            current_step = sort.nextStep();
        } else {
            current_step = sort.prevStep();
        }
        if (current_step != null) {
            if (!this.rgb) {
                selectStep(current_step);
            }
            if (current_step.getSwap()) {
                swapElems(current_step.getFirst(), current_step.getSecond());
            }
            repaint();
        } else {
            timer.stop();
        }
    }

    private void swapElems(int i, int j) {
        Collections.swap(elems, i, j);
    }

    public void actionPerformed(ActionEvent e) {
        if (auto_direction) {
            nextStep();
        } else {
            prevStep();
        }
    }

    private void setRGBColors() {
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
                    } else if (i < sort.array.size() - 6) {
                        elem.setColor(new Color(255, 0, 255 - (255 / (sort.array.size() / 6) * (i % (sort.array.size() / 6)))));
                    } else {
                        elem.setColor(new Color(255, 0, 0));
                    }
                }
            }
        }
    }

    private void setDemoColors() {
        for (SortElem elem : elems) {
            elem.setColor(new Color(190, 190, 190));
        }
    }

    private void selectStep(SortStep step) {
        elems.get(step.getPivot()).setColor(Color.yellow);
        if (step.getFirstChange()) {
            elems.get(step.getFirst()).setColor(Color.blue);
        } else {
            elems.get(step.getFirst()).setColor(Color.orange);
        }

        if (step.getSecondChange()) {
            elems.get(step.getSecond()).setColor(Color.blue);
        } else {
            elems.get(step.getSecond()).setColor(Color.orange);
        }
    }

    private void unselectStep(SortStep step) {
        elems.get(step.getFirst()).setColor(new Color(190, 190, 190));
        elems.get(step.getSecond()).setColor(new Color(190, 190, 190));
        elems.get(step.getPivot()).setColor(new Color(190, 190, 190));
    }
}
