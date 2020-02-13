package me.cxis.algorithms.list.linearlist.example2;

/**
 * 链式存储结构
 */
public class LinerList {

    private Node head = new Node(-1);

    private int size;

    /**
     * O(n)
     * @param index
     * @param data
     */
    public void insert(int index, int data) {
        Node h = head;
        int j = 0;

        while (h != null && j < index - 1) {
            h = h.getNext();
            j++;
        }

        Node newNode = new Node(data);
        newNode.setNext(h.getNext());
        h.setNext(newNode);
        size++;
    }

    /**
     * O(n)
     * @param index
     */
    public void delete(int index) {
        Node h = head;
        int j = 0;

        while (h.getNext() != null && j < index - 1) {
            h = h.getNext();
            j++;
        }

        Node deleteNode = h.getNext();
        h.setNext(deleteNode.getNext());
    }


    public void list() {
        System.out.println(head);
    }

    public static void main(String[] args) {
        LinerList list = new LinerList();
        list.insert(1, 10);
        list.list();
        list.insert(2, 20);
        list.list();
        list.delete(2);
        list.list();
    }

    private class Node {

        private int data;

        private Node next;

        public Node(int data) {
            this(data, null);
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
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
                ", next=" + next +
                '}';
        }
    }


}
