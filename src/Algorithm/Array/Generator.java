package Algorithm.Array;

import java.util.ArrayList;
import java.util.Random;

public class Generator {
    public static ArrayList<Integer> randomArray(int min, int max, int num) {
        ArrayList<Integer> array = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < num; i++) {
            array.add(min + random.nextInt(max - min));
        }
        return array;
    }
}
