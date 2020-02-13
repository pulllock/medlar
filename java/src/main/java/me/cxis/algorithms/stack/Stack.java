package me.cxis.algorithms.stack;

import java.util.Arrays;

public class Stack {

    private int size;

    private int topIndex;

    private int bottomIndex;

    private int[] array;

    public Stack(int size) {
        this.size = size;
        array = new int[size];
        topIndex = bottomIndex = 0;
    }

    public int pop() {
        if (size == 0) {
            throw new RuntimeException("Stack empty");
        }
        size--;
        return array[--topIndex];
    }

    public void push(int data) {
        if (topIndex >= size) {
            throw new RuntimeException("Stack full");
        }

        size++;
        array[topIndex++] = data;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        stack.push(1);
        Arrays.stream(stack.array).forEach(System.out::println);
        System.out.println("-----");
        stack.push(2);
        Arrays.stream(stack.array).forEach(System.out::println);
        System.out.println("-----");
        System.out.println("return:" + stack.pop());
        Arrays.stream(stack.array).forEach(System.out::println);
        System.out.println("-----");
    }
}
