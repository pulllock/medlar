package me.cxis.thinkinginjava.chapter2_7;

/**
 * Created by cheng.xi on 09/02/2017.
 */
public class ShowProperties {
    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
