package algorithm.sort;

import java.util.Comparator;

public class SelectionSort {
    public static <T> T[] sort(T[] array, Comparator<T> cpr) {
        int len = array.length, index, cmp;
        T tmp;
        for (int i = 0; i < len - 1; i++) {
            tmp = array[i];
            index = i;
            for (int j = i + 1; j < len; j++) {
                cmp = cpr.compare(tmp, array[j]);
                if (cmp > 0) {
                    tmp = array[j];
                    index = j;
                }
            }
            if (index != i) {
                tmp = array[index];
                array[index] = array[i];
                array[i] = tmp;
            }
        }
        return array;
    }
    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, Comparable::compareTo);
    }

    public static void main(String[] args) {
        Integer[] array = {3, 2, 4, 5, 2};
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) System.out.print(',');
        }
        System.out.println("}");
        sort(array);
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) System.out.print(',');
        }
        System.out.println("}");
        sort(array, (a, b)-> -a.compareTo(b));
        System.out.print("{");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i != array.length - 1) System.out.print(',');
        }
        System.out.println("}");
    }
}
