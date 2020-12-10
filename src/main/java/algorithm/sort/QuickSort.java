package algorithm.sort;

import java.util.Comparator;

/**
 * Date  :  2020/12/10
 * Author:  YiPing, Wei
 * 时间复杂度 O(n log n) ~ O(n^2)
 * 空间复杂度 O(log n)
 * 不稳定
 * In-place
 **/
public class QuickSort {
    private static <T> T[] quickSort(T[] array, int left, int right, Comparator<T> cpr) {
        if (left < right) {
            int partitionIndex = partition(array, left, right, cpr);
            quickSort(array, left, partitionIndex - 1, cpr);
            quickSort(array, partitionIndex + 1, right, cpr);
        }
        return array;
    }

    private static <T> int partition(T[] array, int left, int right, Comparator<T> cpr) {
        // 这里基准选取可以用随机数
        int pivot = left, index = pivot + 1;
        T tmp;
        for (int i = index; i <= right; i++) {
            if (cpr.compare(array[i], array[pivot]) < 0) {
                tmp = array[i];
                array[i] = array[index];
                array[index++] = tmp;
            }
        }
        tmp = array[pivot];
        array[pivot] = array[index - 1];
        array[index - 1] = tmp;
        return index - 1;
    }

    public static <T> T[] sort(T[] array, Comparator<T> cpr) {
        return quickSort(array, 0, array.length - 1, cpr);
    }

    public static <T extends Comparable<T>> T[] sort(T[] array) {
        return sort(array, Comparable::compareTo);
    }

    public static void main(String[] args) {
        Integer[] array = {3, 2, 4, 5, 4};
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
