package me.cxis;

/**
 * Created by cx on 7/22/16.
 */
public class Foo {

    public static void main(String[] args) {
        String strValue = "ABCDEFG";
        strValue.substring(3);
        strValue.concat("123");
        System.out.println(strValue);
        String value = new String ("ABCDEFG");
        System.out.println(strValue == value);
    }
}
