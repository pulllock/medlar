package me.cxis.optional;

import java.util.Optional;

/**
 * Created by cheng.xi on 2017-06-25 15:16.
 */
public class OptionalTest {
    public static void main(String args[]){
        Optional<String> name = Optional.of("optional");
        if(name.isPresent()){
            System.out.println(name.get());
        }
        //Optional<String> nullName = Optional.of(null);
        Optional<String> empty = Optional.ofNullable(null);
        if(empty.isPresent()){
            System.out.println(empty.get());
        }

        Optional<String> firstName = Optional.of("tom");
        //Optional<String> firstName = Optional.ofNullable(null);
        System.out.println("first name is set? " + firstName.isPresent());
        System.out.println("first name is :" + firstName.orElseGet(() -> "[none]"));
        System.out.println(firstName.map(s -> s).orElse("none"));

    }
}
