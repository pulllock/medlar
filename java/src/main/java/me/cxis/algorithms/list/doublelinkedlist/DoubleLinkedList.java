package me.cxis.algorithms.list.doublelinkedlist;

public class DoubleLinkedList {

    private Node head = new Node();

    private int size;

    public void insert(int index, int data) {
        if (size == 0) {
            Node newNode = new Node(data);
            head.setNext(newNode);
            newNode.setPrev(head);
            size++;
        } else {
            // get element
            Node p = getElement(index);
            // insert
            Node newNode = new Node(data);
            newNode.setPrev(p.getPrev());
            p.getPrev().setNext(newNode);
            newNode.setNext(p);
            p.setPrev(newNode);
            size++;
        }
    }

    public void delete(int index) {
        Node p = getElement(index);
        p.getPrev().setNext(p.getNext());
        Node next = p.getNext();
        if (next != null) {
            next.setPrev(p.getPrev());
        }
        size--;
    }

    private Node getElement(int index) {
        Node p = head.getNext();
        int i = 1;
        while (p != null && i < index) {
            p = p.getNext();
            i++;
        }
        return p;
    }

    private void list() {
        Node p = head;
        int i = 0;
        while (p != null && i < size) {
            p = p.getNext();
            i++;
            System.out.println(p.getData());
        }
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.insert(1, 10);
        list.list();
        System.out.println("-----");
        list.insert(1, 20);
        list.list();
        System.out.println("-----");
        list.insert(2, 30);
        list.list();
        System.out.println("-----");
        list.delete(2);
        list.list();
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
