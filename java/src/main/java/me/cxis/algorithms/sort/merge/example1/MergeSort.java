package me.cxis.algorithms.sort.merge.example1;

import java.util.Arrays;

/**
 * O(N logN)
 * 分治 递归
 */
public class MergeSort {

    public void mergeSort(int[] originArray) {
        // 临时数组，可以减少每次排序都需要重新生成新的数组
        int[] tempArray = new int[originArray.length];
        mergeSort(originArray, tempArray, 0, originArray.length - 1);
    }

    private void mergeSort(int[] originArray, int[] tempArray, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(originArray, tempArray, left, middle);
            mergeSort(originArray, tempArray, middle + 1, right);
            merge(originArray, tempArray, left, middle, right);
        }
    }

    private void merge(int[] originArray, int[] tempArray, int left, int middle, int right) {
        int leftStart = left;
        int rightStart = middle + 1;
        int tempIndex = 0;

        while (leftStart <= middle && rightStart <= right) {
            if (originArray[leftStart] <= originArray[rightStart]) {
                tempArray[tempIndex++] = originArray[leftStart++];
            } else {
                tempArray[tempIndex++] = originArray[rightStart++];
            }
        }

        while (leftStart <= middle) {
            tempArray[tempIndex++] = originArray[leftStart++];
        }

        while (rightStart <= right) {
            tempArray[tempIndex++] = originArray[rightStart++];
        }

        tempIndex = 0;

        while (left <= right) {
            originArray[left++] = tempArray[tempIndex++];
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 3, 2, 8, 4, 5, 7};
        Arrays.stream(array).forEach(System.out::println);
        new MergeSort().mergeSort(array);
        System.out.println("---------");
        Arrays.stream(array).forEach(System.out::println);
    }
}
