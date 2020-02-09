package me.cxis.algorithms.sort.insertion.example1;

public class ShellSort<T extends Comparable> {

    public void shellSort(T[] array) {
        int j;

        // 增量gap，gap逐步减少
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            // 从gap开始，逐个对其所在组进行插入排序
            for (int i = gap; i < array.length; i++) {
                T temp = array[i];
                for (j = i; j >= gap && temp.compareTo(array[j - gap]) < 0; j -= gap) {
                    array[j] = array[j - gap];
                }
                array[j] = temp;
            }
        }
    }
}
