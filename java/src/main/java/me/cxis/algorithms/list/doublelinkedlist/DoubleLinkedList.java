package me.cxis.algorithms.list.doublelinkedlist;

public class DoubleLinkedList {

    private Node head = new Node();

    private int size;

    public void insert(int index, int data) {
        // get element

        // insert
    }



    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insert(1, 10);
        System.out.println(list.head);
        list.insert(2, 20);
        System.out.println(list.head);
        list.insert(2, 30);
        System.out.println(list.head);
    }

    private class Node {

        public int data;

        public Node prev;

        public Node next;

        public Node() {
            this(-1);
        }

        public Node(int data) {
            this(data, null, null);
        }

        public Node(int data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                "data=" + data +
                ", prev=" + prev +
                ", next=" + next +
                '}';
        }
    }
}
