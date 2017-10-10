package me.cxis.lang;

import java.util.Map;
import java.util.Properties;

/**
 * Created by cheng.xi on 16/12/2016.
 */
public class SystemTest {
    public static void main(String[] args) {
        Map<String,String> map = System.getenv();
        System.out.println(map.toString());

        Properties properties = System.getProperties();
        System.out.println(properties.toString());

        System.out.println(System.lineSeparator());
    }
}
