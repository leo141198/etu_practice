package Graphics;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JMenuBar implements ActionListener {
    private JMenu dataMenu;
    private JMenu algorithmMenu;
    private JMenu displayMenu;

    private JMenuItem openFile;
    private JMenuItem generate;

    private JMenu selectColor;
    private JRadioButtonMenuItem colorRGB;
    private JRadioButtonMenuItem colorDemo;

    public Menu() {
        initDataMenu();
        algorithmMenu = new JMenu("Алгоритм");
        initDisplayMenu();
        this.add(algorithmMenu);


    }

    /**
     * Инициализация меню управления данными
     */
    private void initDataMenu() {
        dataMenu = new JMenu("Данные");

        openFile = new JMenuItem("Прочитать из файла");
        dataMenu.add(openFile);

        generate = new JMenuItem("Сгенерировать");
        dataMenu.add(generate);

        this.add(dataMenu);
    }

    /**
     * Инициализация меню управления отображением
     */
    private void initDisplayMenu() {
        displayMenu = new JMenu("Отображение");
        selectColor = new JMenu("Выбор цветов");

        colorRGB = new JRadioButtonMenuItem("RGB");
        colorRGB.setSelected(false);
        colorRGB.addActionListener(this);
        selectColor.add(colorRGB);

        colorDemo = new JRadioButtonMenuItem("Учебные");
        colorDemo.setSelected(true);
        colorDemo.addActionListener(this);
        selectColor.add(colorDemo);

        displayMenu.add(selectColor);
        this.add(displayMenu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == colorRGB) {
            if (colorRGB.isSelected()) {
                setRGB(true);
            } else {
                setRGB(false);
            }
        } else if (e.getSource() == colorDemo) {
            if (colorDemo.isSelected()) {
                setRGB(false);
            } else {
                setRGB(true);
            }
        }
    }

    private void setRGB(boolean rgb) {
        Form parent = (Form) SwingUtilities.getWindowAncestor(this);
        if (rgb) {
            colorDemo.setSelected(false);
            colorRGB.setSelected(true);
            parent.m.setRGB(true);
        } else {
            colorDemo.setSelected(true);
            colorRGB.setSelected(false);
            parent.m.setRGB(false);
        }
    }
}
