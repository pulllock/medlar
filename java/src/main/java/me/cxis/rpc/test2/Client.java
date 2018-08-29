package me.cxis.rpc.test2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * Created by cheng.xi on 17-2-24.
 */
public class Client {
    public static void main(String[] args) {
        //调用远程对象
        try {
            UserService userService = (UserService) Naming.lookup("rmi://127.0.0.1:8888/UserService");
            System.out.println(userService.getUserByName("xiaoming").getName());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
