package me.cxis.algorithms.sort.merge.example2;

import java.util.Arrays;

public class MergeSort {

    public void mergeSort(Integer[] array) {
        Integer[] temp = new Integer[array.length];
        mergeSort(array, temp, 0, array.length - 1);
    }

    private void mergeSort(Integer[] array, Integer[] temp, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(array, temp, left, middle);
            mergeSort(array, temp, middle + 1, right);
            merge(array, temp, left, middle, right);
        }

    }

    private void merge(Integer[] array, Integer[] temp, int left, int middle, int right) {
        int leftStart = left;
        int leftEnd = middle;
        int rightStart = middle + 1;
        int rightEnd = right;
        int tempIndex = 0;
        while (leftStart <= middle && rightStart <= right) {
            if (array[leftStart] < array[rightStart]) {
                temp[tempIndex] = array[leftStart];
                leftStart++;
                tempIndex++;
            } else {
                temp[tempIndex] = array[rightStart];
                rightStart++;
                tempIndex++;
            }
        }

        while (leftStart <= middle) {
            temp[tempIndex] = array[leftStart];
            leftStart++;
            tempIndex++;
        }

        while (rightStart <= right) {
            temp[tempIndex] = array[rightStart];
            rightStart++;
            tempIndex++;
        }

        // 写回到原来的数组中
        tempIndex = 0;
        while (left <= right) {
            array[left] = temp[tempIndex];
            left++;
            tempIndex++;
        }
    }

    public static void main(String[] args) {
        Integer[] array = {1, 3, 2, 8, 4, 5, 7};
        System.out.println("before: " + Arrays.asList(array));
        new MergeSort().mergeSort(array);
        System.out.println("---------");
        System.out.println("after: " + Arrays.asList(array));
    }
}
