package me.cxis.lang;

/**
 * Created by cheng.xi on 16/12/2016.
 */
public class StringBufferTest {

    public static void main(String[] args) {
        StringBuffer originStr = new StringBuffer("abcdefghijklmn");
        System.out.println("Origin:" + originStr.toString());
        System.out.println("After replace : " + originStr.replace(2,8,"xxx"));

    }
}
