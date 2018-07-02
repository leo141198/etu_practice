package Graphics;

import Algorithm.Array.Generator;
import Algorithm.Sort.*;
import Algorithm.Sort.Sort;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Form extends JFrame implements ActionListener, ChangeListener {
    protected JPanel formView;
    protected JButton button_pause;
    protected JPanel map;
    protected JSlider slider_delay;
    private JButton button_start;
    private JButton button_prev_step;
    private JButton button_next_step;
    private JButton button_next_auto;
    private JButton button_prev_auto;
    private JLabel label_delay;
    private Menu menuBar;

    protected Map m;
    protected Sort sort;
    protected ArrayList<Integer> array;

    public Form() {
        setContentPane(formView);
        setVisible(true);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 300));

        button_start.addActionListener(this);
        button_pause.addActionListener(this);

        button_next_step.addActionListener(this);
        button_prev_step.addActionListener(this);

        button_next_auto.addActionListener(this);
        button_prev_auto.addActionListener(this);

        slider_delay.addChangeListener(this);

    }

    /**
     * Инициализация кастомных элементов интерфейса
     */
    private void createUIComponents() {
        array = Generator.randomArray(0, 1000, 100);
        sort = new QuickSortHoara(array);
        m = new Map(array, sort);
        map = m;
        menuBar = new Menu();
        this.setJMenuBar(menuBar);
    }

    /**
     * Обработка собьытий интерфейса
     * @param e событие
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_start || e.getSource() == button_next_auto) {
            m.setAutoDirection(true);
            m.timerStart();
        } else if (e.getSource() == button_pause) {
            m.timerStop();
        } else if (e.getSource() == button_next_step) {
            m.nextStep();
        } else if (e.getSource() == button_prev_step) {
            m.prevStep();
        } else if (e.getSource() == button_prev_auto) {
            m.setAutoDirection(false);
            m.timerStart();
        }
    }

    /**
     * Обработка событий слайдера
     * @param e событие
     */
    @Override
    public void stateChanged(ChangeEvent e) {
        if(e.getSource() == slider_delay) {
            label_delay.setText(slider_delay.getValue() + " мс.");
            m.setTimerDelay(slider_delay.getValue());
        }
    }

    protected void setArray(ArrayList<Integer> array) {
        this.array = array;
        Object sort = null;
        try {
            sort =Class.forName(this.sort.getClass().getName()).getConstructor(ArrayList.class).newInstance(this.array);
        } catch (Exception e){

        }
        setSort((Sort) sort);
    }

    protected void setSort(Sort sort) {
        this.sort = sort;
        m.init(array, sort);
    }
}