package algorithm.sort;

import java.util.Comparator;

/**
 * 时间复杂度 O(n) ~ O(n^2)
 * 空间复杂度 O(1)
 * 稳定
 * In-place
 */
public class BubbleSort {
    public static <T> T[] sort(T[] array, Comparator<T> cpr) {
        int len = array.length, cmp;
        T tmp;
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < len - i; j++) {
                cmp = cpr.compare(array[j], array[j + 1]);
                if (cmp > 0) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                }
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, Comparable::compareTo);
    }

    public static void main(String[] args) {
        Integer[] array = {3, 2, 4, 5, 1};
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
