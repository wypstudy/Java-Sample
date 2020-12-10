package algorithm.sort;

import java.util.Comparator;

/**
 * Date  :  2020/12/10
 * Author:  YiPing, Wei
 * 时间复杂度 O(n) ~ O(n^2)
 * 空间复杂度 O(1)
 * 稳定
 * In-place
 **/
public class InsertionSort {
    public static <T> T[] sort(T[] array, Comparator<T> cpr) {
        int len = array.length, j;
        T tmp;
        for (int i = 1; i < len; i++) {
            tmp = array[i];
            j = i;
            while (j > 0 && cpr.compare(tmp,array[j-1]) < 0) {
                array[j] = array[j-1];
                j--;
            }
            if (j != i) {
                array[j] = tmp;
            }
        }
        return array;
    }

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, Comparable::compareTo);
    }

    public static void main(String[] args) {
        Integer[] array = {3, 2, 4, 5, 3};
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
