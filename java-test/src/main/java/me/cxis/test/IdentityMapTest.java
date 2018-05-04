package me.cxis.test;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * Created by cheng.xi on 2017-05-25 17:18.
 */
public class IdentityMapTest {

    public static void main(String[] args) {
        String key1 = new String ("key");
        //String key2 = new String ( "key");
        String key2 = key1;

        System.out.println("key1 == key2 : " + (key1 == key2));
        System.out.println("key1.equals(key2) : " + (key1.equals(key2)));

        Map<String,Object> hashMap = new HashMap<>();
        hashMap.put(key1,"xxx");
        hashMap.put(key2,"yyy");
        System.out.println(hashMap.size() + " : " + hashMap);

        Map<String,Object> identityMap = new IdentityHashMap<>();
        identityMap.put(key1,"xxx");
        identityMap.put(key2,"yyy");
        System.out.println(identityMap.size() + " : " + identityMap);
    }
}
