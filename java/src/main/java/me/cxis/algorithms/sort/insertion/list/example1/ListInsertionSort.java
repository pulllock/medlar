package me.cxis.algorithms.sort.insertion.list.example1;

import java.util.Arrays;

/**
 * 表插入排序
 */
public class ListInsertionSort {

    public void listInsertionSort(Node[] array) {
        // 中间数组
        Node[] back = new Node[array.length + 1];

        // 将back数组初始化
        for (int i = 0; i < array.length; i++) {
            Node node = new Node();
            node.setData(array[i].getData());
            node.setNext(0);
            back[i + 1] = node;
        }

        // 头结点和第一个结点构成循环链表
        Node head = new Node();
        head.setData(9999999);
        head.setNext(1);
        back[0] = head;

        // 指向待比较结点的前一个
        int pre;
        // 指向待比较结点
        int current;
        for (int i = 2; i <  back.length; i++) {
            pre = 0;
            current = back[pre].getNext();

            while (back[i].getData() >= back[current].getData()) {
                pre = current;
                current = back[pre].getNext();
            }

            back[i].setNext(current);
            back[pre].setNext(i);
        }

        current = back[0].getNext();
        int i = 0;
        while (current != 0) {
            array[i++].setData(back[current].getData());
            current = back[current].getNext();
        }

    }

    private static class Node {

        /**
         * 数据
         */
        private Integer data;

        /**
         * 静态链表的下一个结点
         */
        private int next;

        public Integer getData() {
            return data;
        }

        public void setData(Integer data) {
            this.data = data;
        }

        public int getNext() {
            return next;
        }

        public void setNext(int next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + data +
                ", next=" + next +
                '}';
        }
    }

    public static void main(String[] args) {
        Integer[] array = {8, 3, 6, 2, 4, 5, 7, 1, 9, 0};
        Node[] nodes = new Node[array.length];
        for (int i = 0; i < array.length; i++) {
            Node node = new Node();
            node.setData(array[i]);
            node.setNext(0);
            nodes[i] = node;
        }
        System.out.println("before: " + Arrays.asList(nodes));

        ListInsertionSort sort = new ListInsertionSort();
        sort.listInsertionSort(nodes);
        System.out.println("after: " + Arrays.asList(nodes));

    }
}
