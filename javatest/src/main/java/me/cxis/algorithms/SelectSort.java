package me.cxis.algorithms;

/**
 * Created by cx on 7/20/16.
 * 选择排序
 */
public class SelectSort {

    public static int[] selectSort(int[] source){
        for(int i = 0; i < source.length - 1; i++){
            int min = i;
            for(int j = i + 1; j < source.length; j++){
                if(source[j] < source[min]){
                    min = j;
                }
            }

            if(i != min){
                int temp = source[i];
                source[i] = source[min];
                source[min] = temp;
            }
        }


        return source;
    }

    public static void main(String[] args) {
        int[] source = {1,54,6,3,79,34,12,45};
        long beginTime = System.nanoTime();
        int[] result = selectSort(source);
        long endTime = System.nanoTime();
        System.out.println(endTime - beginTime);
        for(int i = 0; i < result.length; i++){
            System.out.println(result[i]);
        }
    }
}
