package me.cxis.set.treeset;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created by cheng.xi on 2017-04-28 10:21.
 */
public class Main {
    public static void main(String[] args) {
        test1();
        test2();
    }

    private static void test2() {
        TreeSet treeSet = new TreeSet();
        treeSet.add(1);
        treeSet.add(4);
        treeSet.add(5);
        treeSet.add(2);
        treeSet.add(3);

        Iterator iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    private static void test1() {
        TreeSet<People> treeSet = new TreeSet();
        treeSet.add(new People(1,"zhang"));
        treeSet.add(new People(6,"li"));
        treeSet.add(new People(2,"wang"));
        treeSet.add(new People(4,"sun"));

        Iterator<People> iterator = treeSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }
}
