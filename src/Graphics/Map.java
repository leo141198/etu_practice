package Graphics;

import Algorithm.Sort.QuickSortHoara;
import Algorithm.Structure.SortStep;
import Algorithm.Sort.Sort;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class Map extends JPanel implements ActionListener {
    private ArrayList<SortElem> elems;
    private Integer max_value;
    private Sort sort;
    private Boolean rgb = false;
    private Timer timer;
    private Integer delay = 100;
    private Boolean auto_direction = true;
    private SortStep current_step;

    public Map(ArrayList<Integer> array, Sort sort) {
        init(array, sort);
    }

    /**
     * Инициализаци отображения (констуктор не подходит, т.к. есть необходимость переинициализации
     *
     * @param array массив чисел
     * @param sort  объект сортировщика
     */
    protected void init(ArrayList<Integer> array, Sort sort) {
        if (timer != null) {
            timer.stop();
        }
        this.max_value = Collections.max(array);
        elems = new ArrayList<SortElem>();
        for (Integer num : array) {
            this.elems.add(new SortElem(num, 0, 0, 0, 0));
        }
        this.sort = sort;
        this.sort.sort();
        if (rgb) {
            setRGBColors();
        } else {
            setDemoColors();
        }
        repaint();
        timer = new Timer(delay, this);
    }

    /**
     * Отрисовка компоненты
     *
     * @param g графика родительского объекта
     */
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



    /* УПРАВЛЕНИЕ ТАЙМЕРОМ  */

    /**
     * Установк задежрки таймера
     *
     * @param delay задержка
     */
    protected void setTimerDelay(int delay) {
        this.delay = delay;
        timer.setDelay(this.delay);
    }

    /**
     * Запуск таймера
     */
    protected void timerStart() {
        timer.start();
    }

    /**
     * Установка таймера
     */
    protected void timerStop() {
        timer.stop();
    }


    /* УПРАВЛЕНИЕ ШАГАМИ */

    /**
     * Установка направление отображения шагов
     *
     * @param direction направление
     */
    protected void setAutoDirection(boolean direction) {
        this.auto_direction = direction;
    }

    /**
     * Функция, триггеруемая таймером
     *
     * @param e событие
     */
    public void actionPerformed(ActionEvent e) {
        if (auto_direction) {
            nextStep();
        } else {
            prevStep();
        }
    }

    /**
     * Отобразить следующий шаг алгоритма
     */
    protected void nextStep() {
        makeStep(true);
    }

    /**
     * Отобразить предыдущий шаг алгоритма
     */
    protected void prevStep() {
        makeStep(false);
    }

    /**
     * Отобразить шаг алгоритма
     *
     * @param next направление (вперед / назад)
     */
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
            if (current_step.getFinalPos()) {
                elems.get(current_step.getFirst()).setFinalPos(true);
            }
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

    /**
     * Поменять элементы местами
     *
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swapElems(int i, int j) {
        Collections.swap(elems, i, j);
    }


    /* ОТРИСОВКА ШАГОВ */

    /**
     * Выбор элементов, отвечающих за текущий шаг алгоритма
     *
     * @param step шаг алгоритма
     */
    private void selectStep(SortStep step) {
        if (step.getFinalPos()) {
            elems.get(step.getFirst()).setColor(Color.green);
        } else {
            if (step.getPivot() != null) {
                elems.get(step.getPivot()).setColor(Color.yellow);
            }
            if (step.getFirst() != null) {
                if (step.getFirstChange()) {
                    elems.get(step.getFirst()).setColor(Color.blue);
                } else {
                    elems.get(step.getFirst()).setColor(Color.orange);
                }
            }
            if (step.getSecond() != null) {
                if (step.getSecondChange()) {
                    elems.get(step.getSecond()).setColor(Color.blue);
                } else {
                    elems.get(step.getSecond()).setColor(Color.orange);
                }
            }
        }
    }

    /**
     * Отмена выбора элементов, отвечающих за шаг алгоритма (нужно для перехода к следующему шагу)
     *
     * @param step шаг алгоритма
     */
    private void unselectStep(SortStep step) {
        if (!step.getFinalPos()) {
            if (step.getFirst() != null) {
                elems.get(step.getFirst()).setColor(new Color(190, 190, 190));
            }
            if (step.getSecond() != null) {
                elems.get(step.getSecond()).setColor(new Color(190, 190, 190));
            }
            if (step.getPivot() != null) {
                elems.get(step.getPivot()).setColor(new Color(190, 190, 190));
            }
        }
    }

    /* ОТРИСОВКА ЦВЕТОВ */

    /**
     * Установка цветого паттерна элементов
     *
     * @param rgb RGB / demo
     */
    protected void setRGB(boolean rgb) {
        this.rgb = rgb;
        if (rgb) {
            setRGBColors();
        } else {
            setDemoColors();
        }
        repaint();
    }

    /**
     * Установка цветов элементов в диапазон RGB (в результате сортировки получится паттерн RGB)
     */
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

    /**
     * Установка цветов элементов в учебный режим для отображения перемещений
     */
    private void setDemoColors() {
        for (SortElem elem : elems) {
            if (elem.getFinalPos()) {
                elem.setColor(Color.green);
            } else {
                elem.setColor(new Color(190, 190, 190));
            }
        }
    }
}
