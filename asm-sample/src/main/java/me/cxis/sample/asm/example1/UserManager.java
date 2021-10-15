package me.cxis.sample.asm.example1;

public class UserManager {

    public String queryUserName(Long userId) {
        System.out.println("query user name by user id");
        return"_name";
    }
}
