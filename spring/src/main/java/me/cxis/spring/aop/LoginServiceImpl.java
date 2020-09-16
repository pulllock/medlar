package me.cxis.spring.aop;

public class LoginServiceImpl implements LoginService {

    public String login(String userName){
        System.out.println("autoproxy:正在登录");
        return "success";
    }
}