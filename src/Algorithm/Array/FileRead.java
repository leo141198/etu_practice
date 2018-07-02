package Algorithm.Array;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileRead {
    /**
     * Чтение массива чисел из файла
     * @return массив считанных элементов
     */
    public static ArrayList<Integer> readFile() {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(null);
        File file = jfc.getSelectedFile();
        Scanner scan;
        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException exp) {
            return null;
        }
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        while (scan.hasNextInt()) {
            arrayList.add(scan.nextInt());
        }
        return arrayList;
    }
}
