package me.cxis.algorithms.linearlist.example1;

import java.util.Arrays;

/**
 * 顺序存储结构
 */
public class LinearList {

    /**
     * O(n)
     * @param array
     * @param index
     * @param data
     */
    public void insert(Integer[] array, int index, Integer data) {
        int realIndex = index - 1;
        for (int j = array.length - 1; j >= realIndex; j--) {
            array[j] = array[j - 1];
        }
        array[realIndex] = data;
    }

    /**
     * O(n)
     * @param array
     * @param index
     * @return
     */
    public Integer delete(Integer[] array, int index) {
        int realIndex = index - 1;
        Integer deletedValue = array[realIndex];
        array[realIndex] = null;
        for (int i = realIndex + 1; i < array.length; i++) {
            array[i - 1] = array[i];
        }
        return deletedValue;
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[10];
        array[0] = 1;
        array[1] = 3;
        array[2] = 9;
        System.out.println("origin: " + Arrays.asList(array));
        LinearList linearList = new LinearList();
        linearList.insert(array, 2, 8);
        System.out.println("after insert:" + Arrays.asList(array));
        linearList.delete(array, 3);
        System.out.println("after delete: " + Arrays.asList(array));
    }
}
