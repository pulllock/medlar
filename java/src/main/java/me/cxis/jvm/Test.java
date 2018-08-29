package me.cxis.jvm;

/**
 * Created by cheng.xi on 17/01/2017.
 * -Xms60m
 * -Xmx60m
 * -Xmn20m
 * -XX:NewRatio=2( 若 Xms = Xmx, 并且设定了 Xmn, 那么该项配置就不需要配置了 )
 * -XX:SurvivorRatio=8
 * -XX:PermSize=30m
 * -XX:MaxPermSize=30m
 * -XX:+PrintGCDetails
 */
public class Test {

    public static void main(String[] args) {
        new Test().doTest();
    }

    private void doTest() {
        Integer M = new Integer(1024 * 1024 * 1);//兆M
        byte[] bytes = new byte[1 * M];//1M的内存空间
        bytes = null;//断开引用
        System.gc();//通知GC收集垃圾
        bytes = new byte[1 * M];//1M
        bytes = new byte[1 * M];//1M
        System.gc();
        System.out.println();
    }
}
