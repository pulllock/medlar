package me.cxis.algorithms;

/**
 * Created by cx on 7/20/16.
 * 冒泡排序
 *
 * 基本思想：在要排序的一组数中，对当前还未排好序的范围内的全部数，
 * 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
 * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
 */
public class BubbleSort {

    public static int[] bubbleSort(int[] source){
        for(int i = 0; i < source.length -1; i++){
            for(int j = 0; j < source.length - i -1; j++){
                if(source[j] > source[j + 1]){
                    int temp = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = temp;
                }
            }
        }

        return source;
    }




    public static void main(String[] args) {
        int[] source = {1,54,6,3,79,34,12,45};
        long beginTime = System.nanoTime();
        int[] result = bubbleSort(source);
        long endTime = System.nanoTime();
        System.out.println(endTime - beginTime);
        for(int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }
    }
}
