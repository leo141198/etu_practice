package Algorithm.Array;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    /**
     * Генерация рандомного массива из n элементов
     * @param min минимальное значение
     * @param max максимальное значение
     * @param num количество элементов
     * @return массив сгенерированных элементов
     */
    public static ArrayList<Integer> randomArray(int min, int max, int num) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            array.add(min + random.nextInt(max - min));
        }
        return array;
    }
}
