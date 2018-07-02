package Graphics;

import Algorithm.Sort.BubleSort;
import Algorithm.Sort.QuickSortHoara;
import Algorithm.Sort.QuickSortLomuto;

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

    private JMenu selectAlgorithm;
    private JRadioButtonMenuItem algoHoara;
    private JRadioButtonMenuItem algoLomuto;
    private JRadioButtonMenuItem algoBuble;

    public Menu() {
        initDataMenu();
        initDisplayMenu();
        initAlgorithmMenu();
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

    private void initAlgorithmMenu() {
        algorithmMenu = new JMenu("Алгоритм");

        selectAlgorithm = new JMenu("Выбор алгоритма");

        algoHoara = new JRadioButtonMenuItem("qSort (Хоара)");
        algoHoara.setSelected(true);
        algoHoara.addActionListener(this);
        selectAlgorithm.add(algoHoara);

        algoLomuto = new JRadioButtonMenuItem("qSort (Ломуто)");
        algoLomuto.setSelected(false);
        algoLomuto.addActionListener(this);
        selectAlgorithm.add(algoLomuto);

        algoBuble = new JRadioButtonMenuItem("BubbleSort");
        algoBuble.setSelected(false);
        algoBuble.addActionListener(this);
        selectAlgorithm.add(algoBuble);

        algorithmMenu.add(selectAlgorithm);
        this.add(algorithmMenu);
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
                colorRGB.setSelected(true);
            }
        } else if (e.getSource() == colorDemo) {
            if (colorDemo.isSelected()) {
                setRGB(false);
            } else {
                colorDemo.setSelected(true);
            }
        } else if (e.getSource() == algoHoara) {
            if (algoHoara.isSelected()) {
                setAlgo(1);
            } else {
                algoHoara.setSelected(true);
            }
        } else if (e.getSource() == algoLomuto) {
            if (algoLomuto.isSelected()) {
                setAlgo(2);
            } else {
                algoLomuto.setSelected(true);
            }
        } else if (e.getSource() == algoBuble) {
            if (algoBuble.isSelected()) {
                setAlgo(3);
            } else {
                algoBuble.setSelected(true);
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

    private void setAlgo(int algo) {
        Form parent = (Form) SwingUtilities.getWindowAncestor(this);
        switch (algo) {
            case 1:
                parent.setSort(new QuickSortHoara(parent.array));
                algoLomuto.setSelected(false);
                algoBuble.setSelected(false);
                break;
            case 2:
                parent.setSort(new QuickSortLomuto(parent.array));
                algoHoara.setSelected(false);
                algoBuble.setSelected(false);
                break;
            case 3:
                parent.setSort(new BubleSort(parent.array));
                algoHoara.setSelected(false);
                algoLomuto.setSelected(false);
                break;
        }
    }
}
