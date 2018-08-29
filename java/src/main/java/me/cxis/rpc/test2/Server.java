package me.cxis.rpc.test2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class Server {
    public static void main(String[] args) {
        try {
            UserService userService = new UserServiceImpl();
            //注册通讯端口
            LocateRegistry.createRegistry(8888);
            //注册service
            Naming.rebind("rmi://127.0.0.1:8888/UserService",userService);
            System.out.println("服务端已启动，可以远程调用UserService");
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
