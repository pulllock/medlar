//package me.cxis.gof.proxy_pattern.dynamic_proxy;
//
//public class Client {
//
//    public static void main(String[] args) {
//        DeleteUserService deleteUserService = (DeleteUserService) new DeleteUserServiceProxyHandler(new DeleteUserServiceImpl()).newProxyInstance();
//        deleteUserService.deleteUserById(11);
//        deleteUserService.deleteUserById(101);
//    }
//}
