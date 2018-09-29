package me.cxis.gof.proxy_pattern.cglib_proxy;

public class Client {

    public static void main(String[] args) {
        DeleteUserService deleteUserService = (DeleteUserService) new DeleteUserServiceImplProxy().newProxyInstance(DeleteUserServiceImpl.class);
        deleteUserService.deleteUserById(11);
        deleteUserService.deleteUserById(101);
    }
}
