package me.cxis.gof.proxy_pattern.static_proxy;

public class Client {

    public static void main(String[] args) {
        DeleteUserService deleteUserService = new DeleteUserServiceProxy();
        deleteUserService.deleteUserById(1);
        deleteUserService.deleteUserById(101);
    }
}
