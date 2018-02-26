package me.cxis;

import java.util.Arrays;

public class BubbleSort_2 {

    public static int[] bubbleSort(int[] originArray) {
        System.out.println("排序之前的数组：" + Arrays.toString(originArray));
        for (int i = 1; i < originArray.length; i++) {
            for (int j = originArray.length - 1; j > i - 1; j-- ) {
                if (originArray[j] < originArray[j-1]) {
                    int tmp = originArray[j-1];
                    originArray[j-1] = originArray[j];
                    originArray[j] = tmp;
                }
            }
            System.out.println("第" + (i + 1) + "次排序后的数组：" + Arrays.toString(originArray));
        }
        System.out.println("排序之后的数组：" + Arrays.toString(originArray));
        return originArray;
    }

    public static void main(String[] args) {
        int[] originArray = {3, 4, 1, 9, 5, 6, 2};
        bubbleSort(originArray);

    }
}
