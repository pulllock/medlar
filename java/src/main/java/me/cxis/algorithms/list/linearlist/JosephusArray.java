package me.cxis.algorithms.list.linearlist;

import java.util.Arrays;

/**
 * 使用数组实现约瑟夫环
 */
public class JosephusArray {

    public void josephus(int numbers, int start, int step) {
        Integer[] array = new Integer[numbers];
        for (int i = 0; i < array.length; i++) {
            array[i] = 1 + i;
        }

        System.out.println("犯人数组：" + Arrays.toString(array));

        int index = start - 1;
        int length = array.length;
        while (length > 1) {
            index = (index + step - 1) % array.length;
            System.out.println("删除：" + array[index]);
            // 将移除位置之后的元素前移
            System.arraycopy(array, index + 1, array, index, array.length - 1 - index);
            length--;
            array = Arrays.copyOf(array, length);
            System.out.println("剩余：" + Arrays.toString(array));
        }

        System.out.println("剩余的犯人：" + array[0]);
    }

    public static void main(String[] args) {
        new JosephusArray().josephus(5, 1, 2);
    }
}
