package Graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class Form extends JFrame implements ActionListener {
    protected JPanel formView;
    protected JButton button_pause;
    protected JPanel map;
    protected JSlider slider_delay;
    private JButton button_start;
    private JButton button_prev_step;
    private JButton button_next_step;
    private JButton button_next_auto;
    private JButton button_prev_auto;
    private Menu menuBar;
    private Map m;

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
    }

    /**
     * Инициализация кастомных элементов интерфейса
     */
    private void createUIComponents() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            list.add(random.nextInt(50));
        }
        m = new Map(list);
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
}