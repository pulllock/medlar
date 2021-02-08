package me.cxis.algorithms.list.linearlist;

import java.util.ArrayList;
import java.util.List;

/**
 * 约瑟夫环
 */
public class Josephus {

    private List<String> list;

    public Josephus(int number, int start, int distance) {
        list = new ArrayList<>(number);

        for(int i = 0; i < number; i++){
            list.add(new String((char)('A' + i) + ""));
        }

        System.out.println("约瑟夫环：" + number + "," + start + "," + distance + "," + list.toString());

        int index = start - 1;
        while(list.size() > 1){
            index = (index + distance - 1) % list.size();
            System.out.println("删除：" + list.remove(index) + ",剩余：" + list.toString());
        }

        System.out.println("被赦免的是：" + list.get(0));
    }

    public static void main(String[] args) {
        new Josephus(5,1,2);
    }
}
