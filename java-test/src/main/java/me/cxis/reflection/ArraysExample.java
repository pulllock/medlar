package me.cxis.reflection;

import java.lang.reflect.Array;

/**
 * Created by cheng.xi on 2017-10-14 14:44.
 */
public class ArraysExample {
    public static void main(String[] args) {
        int[] intArray = (int[]) Array.newInstance(int.class, 3);

        Array.set(intArray, 0, 123);
        Array.set(intArray, 1, 333);
        Array.set(intArray, 2, 444);

        System.out.println(Array.get(intArray, 0));
        System.out.println(Array.get(intArray, 1));
        System.out.println(Array.get(intArray, 2));


    }
}
