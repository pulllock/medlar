package me.cxis;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Node tail = new Node();

        for(Node t = tail,p = t;;){
            System.out.println(tail);
            System.out.println(t);
            System.out.println(p);
            System.out.println(tail == p);
            System.out.println(tail == t);
            System.out.println(t == p);
            p.next = new Node();
            System.out.println(tail);
            System.out.println(t);
            System.out.println(p);
            System.out.println(tail == p);
            System.out.println(tail == t);
            System.out.println(t == p);
            System.out.println(p.next);
            System.out.println(t.next);
            System.out.println(tail.next);
            break;
        }
    }
}
