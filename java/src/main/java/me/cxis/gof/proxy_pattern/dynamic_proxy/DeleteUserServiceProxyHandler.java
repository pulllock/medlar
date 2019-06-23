//package me.cxis.gof.proxy_pattern.dynamic_proxy;
//
//import sun.misc.ProxyGenerator;
//
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.lang.reflect.Proxy;
//
//public class DeleteUserServiceProxyHandler implements InvocationHandler {
//
//    private Object proxied;
//
//    public DeleteUserServiceProxyHandler(Object proxied) {
//        this.proxied = proxied;
//    }
//
//    public Object newProxyInstance() {
//        return Proxy.newProxyInstance(proxied.getClass().getClassLoader(), proxied.getClass().getInterfaces(), this);
//    }
//
//    @Override
//    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//        saveClass(proxy.getClass().getName(), DeleteUserServiceImpl.class, "/$Proxy0.class");
//        if (checkUser((Long) args[0])) {
//            System.out.println(String.format("User: %s has delete permission", args[0]));
//            method.invoke(proxied, args);
//        } else {
//            System.out.println(String.format("User: %s has no delete permission", args[0]));
//        }
//        return null;
//    }
//
//    private void saveClass(String className, Class<?> clazz, String path) {
//        byte[] classFile = ProxyGenerator.generateProxyClass(className, clazz.getInterfaces());
//        FileOutputStream outputStream = null;
//        try {
//            outputStream = new FileOutputStream(path);
//            outputStream.write(classFile);
//            outputStream.flush();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private boolean checkUser(long id) {
//        if (id > 100) {
//            return false;
//        }
//        return true;
//    }
//}
