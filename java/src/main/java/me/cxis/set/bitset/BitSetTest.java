package me.cxis.set.bitset;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * Created by cheng.xi on 2017-05-28 18:55.
 */
public class BitSetTest {
    static final int NUM = 10000000;

    static final int NUM2 = 100000000;

    public static void main(String[] args) {

        Random random = new Random();


        List<Integer> list = new ArrayList<>(NUM);
        for(int i = 0;i < NUM; i++){
            list.add(random.nextInt(NUM2));
        }

        System.out.println("产生的随机数有：" + list.size());
       /* for(int i = 0;i < list.size(); i++){
            System.out.println(list.get(i));
        }*/

        BitSet bitSet = new BitSet(NUM);
        for(int i = 0; i < NUM; i++){
            bitSet.set(list.get(i));
        }

        System.out.println("0~1亿不在上述随机数的有：");
        List<Integer> tmpList = new ArrayList<>(NUM2 - NUM);
        for(int i =0; i < NUM2; i++){
            if(!bitSet.get(i)){
                //System.out.println(i);
                tmpList.add(i);
            }
        }
        System.out.println(tmpList.size());
    }
}
